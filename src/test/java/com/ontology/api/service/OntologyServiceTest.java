package com.ontology.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.ontology.model.Ontology;
import com.ontology.repository.OntologyRepository;
import com.ontology.service.OntologyService;

@RunWith(SpringRunner.class)
public class OntologyServiceTest {
  @Mock
  private OntologyRepository ontologyRepositoryMock;

  @MockBean
  OntologyService ontologyService;


  @Test
  public void shouldfindAllOntologies() {
    List<Ontology> ontologies = new ArrayList<Ontology>();
    ontologies.add(getOntology().orElse(null));
    when(ontologyRepositoryMock.findAll()).thenReturn(ontologies);
    assertTrue(ontologies.size() > 0);
  }

  @Test
  public void shouldfindOntologyById() {
    String ontologyId = "mondo";
    Optional<Ontology> ontology = getOntologyById(ontologyId);
    when(ontologyRepositoryMock.findById(ontologyId)).thenReturn(ontology);
    assertNotNull(ontology.orElse(null));
  }

  @Test
  public void shouldNotfindOntologyById() {
    String ontologyId = null;
    Optional<Ontology> ontology = getOntologyById(ontologyId);
    when(ontologyRepositoryMock.findById(ontologyId)).thenReturn(ontology);
    assertNull(ontology);
  }

  private Optional<Ontology> getOntologyById(String ontologyId) {
    if (ontologyId == null)
      return null;
    String description =
        "AEO is an ontology of anatomical structures that expands CARO, the Common Anatomy Reference Ontology";
    String title = "Anatomical Entity Ontology";
    String synonymProperties = "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym";
    return Optional.of(Ontology.builder().ontologyId(ontologyId).description(description)
        .title(title).synonymProperties(synonymProperties).build());
  }


  private Optional<Ontology> getOntology() {
    String ontologyId = "mondo";
    String description =
        "AEO is an ontology of anatomical structures that expands CARO, the Common Anatomy Reference Ontology";
    String title = "Anatomical Entity Ontology";
    String synonymProperties = "http://www.geneontology.org/formats/oboInOwl#hasExactSynonym";
    return Optional.of(Ontology.builder().ontologyId(ontologyId).description(description)
        .title(title).synonymProperties(synonymProperties).build());
  }

}
