package com.ontology.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ontology.model.AppUser;

@Repository
public interface UserRepository extends MongoRepository<AppUser, Long>{

  Optional<AppUser> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  Boolean existsByName(String name);

  

}
