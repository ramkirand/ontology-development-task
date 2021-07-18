package com.ontology.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ontology.model.Ontology;

@Service
public interface OntologyService {

  Optional<Ontology> getOntologyById(String ontologyId) throws IOException;

  List<Ontology> getOntologies();

}
