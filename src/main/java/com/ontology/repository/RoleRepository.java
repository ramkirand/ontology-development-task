package com.ontology.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ontology.model.ERole;
import com.ontology.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
