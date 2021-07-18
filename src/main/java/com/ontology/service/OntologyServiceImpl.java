package com.ontology.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontology.annotation.TrackExecutionTime;
import com.ontology.exception.ApiRequestException;
import com.ontology.model.Ontology;
import com.ontology.repository.OntologyRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OntologyServiceImpl implements OntologyService {
  private static final String _EMBEDDED = "_embedded";
  private static final String ONTOLOGIES2 = "ontologies";
  private static final String DEFINITION_PROPERTIES = "definitionProperties";
  private static final String SYNONYM_PROPERTIES = "synonymProperties";
  private static final String TITLE = "title";
  private static final String CONFIG = "config";
  private static final String DESCRIPTION = "description";
  private static final String ONTOLOGY_ID = "ontologyId";

  @Value("${HTTPS_WWW_EBI_AC_UK}")
  private String ebiUrl;
  private OlsClient olsClient;
  private OntologyRepository ontologyRepository;


  @Autowired
  OntologyServiceImpl(OntologyRepository ontologyRepository, OlsClient olsClient) {
    this.ontologyRepository = ontologyRepository;
    this.olsClient = olsClient;
  }


  @Cacheable("OntologyCache")
  @TrackExecutionTime
  public Optional<Ontology> getOntologyById(String ontologyId) throws IOException {
    Optional<Ontology> ontology = ontologyRepository.findById(ontologyId);

    if (!ontology.isPresent()) {

      try {
        ResponseEntity<String> ontologyJson =
            olsClient.retrieveOntologyInformation(ebiUrl + ontologyId);
        String str = ontologyJson.getBody().split("<200,")[0];
        ontology = buildOntologyMeta(str);
        ontologyRepository.save(ontology.get());
        log.info("<<<<<<<<<<<<<<<<  Ontology Data Saved Successfully  >>>>>>>>>>>>>>>>>>>>>");
      } catch (Exception ex) {
        throw new ApiRequestException(
            "failed from ols for getOntologyById" + ex.getLocalizedMessage());
      }

    }
    return ontology;
  }

  @Cacheable("OntologyCache")
  public List<Ontology> getOntologies() {
    List<Ontology> ontologies = ontologyRepository.findAll();
    if (ontologies != null && !ontologies.isEmpty())
      return ontologies;
    ontologies = new ArrayList<Ontology>();
    JsonNode node = null;
    try {
      ResponseEntity<String> ontologyJson = olsClient.retrieveOntologyInformation(ebiUrl);
      ObjectMapper mapper = new ObjectMapper();
      node = mapper.readTree(ontologyJson.getBody());
      String jsonStr = node.get(_EMBEDDED).get(ONTOLOGIES2).toString();
      log.info(jsonStr);

      JSONParser jsonParser = new JSONParser();
      Object object = jsonParser.parse(jsonStr);
      JSONArray ontologyJsonlist = (JSONArray) object;

      int listCount = ontologyJsonlist.size();

      for (int i = 0; i < listCount; i++) {
        Optional<Ontology> ontology = buildOntologyMeta(ontologyJsonlist.get(i).toString());
        ontologies.add(ontology.get());
      }
      ontologyRepository.saveAll(ontologies);
      log.info("Saved all, " + ontologies.size() + " records to DB");

    } catch (Exception e) {
      log.info(e.getLocalizedMessage());
      throw new ApiRequestException(e.getLocalizedMessage());
    }
    return ontologies;
  }



  private Optional<Ontology> buildOntologyMeta(String ontologyJson) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(ontologyJson);
    return Optional.of(Ontology.builder().ontologyId(node.get(ONTOLOGY_ID).toString())
        .definitionProperties(node.get(CONFIG).get(DEFINITION_PROPERTIES).toString())
        .synonymProperties(node.get(CONFIG).get(SYNONYM_PROPERTIES).toString())
        .title(node.get(CONFIG).get(TITLE).toString())
        .description(node.get(CONFIG).get(DESCRIPTION).toString()).build());
  }
}
