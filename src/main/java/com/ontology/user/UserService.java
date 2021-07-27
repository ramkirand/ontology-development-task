package com.ontology.user;

import org.springframework.stereotype.Service;
import com.ontology.model.User;

@Service
public interface UserService {
  void createUser(User user);
}
