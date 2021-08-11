package com.ontology.security.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ontology.exception.ApiRequestException;
import com.ontology.model.AppUser;
import com.ontology.model.ERole;
import com.ontology.model.Role;
import com.ontology.repository.UserRepository;
import com.ontology.security.model.JwtResponse;
import com.ontology.security.model.LoginRequest;
import com.ontology.security.model.MessageResponse;
import com.ontology.security.model.SignupRequest;
import com.ontology.security.repository.RoleRepository;
import com.ontology.security.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthenticationController {


  private static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully!";

  private static final String ERROR_USERNAME_IS_ALREADY_TAKEN = "Error: Username is already taken!";

  private static final String ERROR_NAME_IS_ALREADY_IN_USE = "Error: name is already in use!";

  private static final String ERROR_ROLE_IS_NOT_FOUND = "Error: Role is not found.";

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  private JwtUtils jwtUtil;

  @Value("${INVALID_USER}")
  private String invalidUser;

  @GetMapping("/")
  public String welcome() {
    return "Welcome world";
  }


  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerUser(
      @Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {

      return ResponseEntity.badRequest().body(new MessageResponse(ERROR_USERNAME_IS_ALREADY_TAKEN));
    }

    if (userRepository.existsByName(signUpRequest.getName())) {
      return ResponseEntity.badRequest().body(new MessageResponse(ERROR_NAME_IS_ALREADY_IN_USE));
    }

    // Create new user's account
    AppUser user = new AppUser(signUpRequest.getUsername(), signUpRequest.getName(),
        encoder.encode(signUpRequest.getPassword()), signUpRequest.getRole());

    String strRole = signUpRequest.getRole();
    switch (strRole) {
      case "admin":
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
        user.setRole(adminRole.getId());
        break;
      case "mod":
        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
            .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
        user.setRole(modRole.getId());
        break;
      default:
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
        user.setRole(userRole.getId());
    }

    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse(USER_REGISTERED_SUCCESSFULLY));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<JwtResponse> generateToken(@Valid @RequestBody LoginRequest authRequest)
      throws Exception {
    JwtResponse jwtResponse = new JwtResponse();
    try {
      AppUser user = userRepository.findByUsername(authRequest.getUsername()).get();

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          authRequest.getUsername(), authRequest.getPassword()));
      String token = jwtUtil.generateToken(authRequest.getUsername(), user.getName());
      buildLoginFormResponse(jwtResponse, user, token);
    } catch (Exception ex) {
      throw new ApiRequestException(invalidUser + ex.getLocalizedMessage());
    }
    log.info("JWT :" + jwtResponse);
    return ResponseEntity.ok(jwtResponse);
  }


  private void buildLoginFormResponse(JwtResponse jwtResponse, AppUser user, String token) {
    if (user != null && token != null) {
      Optional<Role> role = roleRepository.findById(user.getRole());
      jwtResponse.setToken(token);
      jwtResponse.setName(user.getName());
      jwtResponse.setEmail(user.getUsername());
      jwtResponse.setUsername(user.getUsername());
      jwtResponse.setRole(role.get().getName());
    }

  }

}

