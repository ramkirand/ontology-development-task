package com.ontology.user;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ontology.model.User;

@Service
public interface OntologyUserService {
  Optional<User> createUser(User user);}
