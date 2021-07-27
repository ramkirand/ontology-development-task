package com.ontology.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ontology.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{

}
