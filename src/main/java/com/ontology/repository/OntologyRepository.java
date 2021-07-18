package com.ontology.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ontology.model.Ontology;


@Repository
public interface OntologyRepository extends MongoRepository<Ontology, String> {

}
