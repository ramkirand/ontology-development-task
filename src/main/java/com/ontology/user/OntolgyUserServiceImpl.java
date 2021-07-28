package com.ontology.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ontology.exception.ApiRequestException;
import com.ontology.model.User;
import com.ontology.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OntolgyUserServiceImpl implements OntologyUserService {
  private static final String USER_ALREADY_EXISTS = "User already exists";
  private UserRepository userRepository;

  @Autowired
  public OntolgyUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> createUser(User user) {
    String currentUser = user.getUsername();
    List<User> users = userRepository.findAll();
    users.forEach(l -> {
      if (l.getUsername().equals(currentUser)) {
        throw new ApiRequestException(USER_ALREADY_EXISTS);
      }
    });
    log.info("currentUser is a new User" + currentUser);
    userRepository.save(user);
    user.setPassword("");
    return Optional.of(user);
  }

}
