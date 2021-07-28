package com.ontology.security.controller.model;

import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class LoginRequest {
  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
