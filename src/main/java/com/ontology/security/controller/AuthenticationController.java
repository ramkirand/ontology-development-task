package com.ontology.security.controller;

import java.util.HashSet;
import java.util.Set;
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
import com.ontology.model.ERole;
import com.ontology.model.Role;
import com.ontology.model.User;
import com.ontology.repository.RoleRepository;
import com.ontology.repository.UserRepository;
import com.ontology.security.model.JwtResponse;
import com.ontology.security.model.LoginRequest;
import com.ontology.security.model.MessageResponse;
import com.ontology.security.model.SignupRequest;
import com.ontology.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthenticationController {


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
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByName(signUpRequest.getName())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: name is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), signUpRequest.getName(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<JwtResponse> generateToken(@Valid @RequestBody LoginRequest authRequest)
      throws Exception {
    JwtResponse jwtResponse = new JwtResponse();
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          authRequest.getUsername(), authRequest.getPassword()));
      String token = jwtUtil.generateToken(authRequest.getUsername());
      jwtResponse.setToken(token);
      jwtResponse.setEmail(authRequest.getUsername());

    } catch (Exception ex) {
      jwtResponse.setUsername(authRequest.getUsername());
      throw new ApiRequestException(invalidUser + ex.getLocalizedMessage());
    }
    log.info("JWT :" + jwtResponse);
    return ResponseEntity.ok(jwtResponse);
  }

}

