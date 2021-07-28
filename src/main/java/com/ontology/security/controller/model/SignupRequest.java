package com.ontology.security.controller.model;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  private Set<String> roles;
}
