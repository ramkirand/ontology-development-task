package com.ontology.user;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ontology.model.AppUser;

@Service
public interface OntologyUserService {
  Optional<AppUser> createUser(AppUser user);}
