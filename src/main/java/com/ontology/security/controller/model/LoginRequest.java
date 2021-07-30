package com.ontology.security.controller.model;

import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Component
@Data
public class LoginRequest {
  @NotBlank
  @JsonProperty("Email")
  private String username;
  @NotBlank
  @JsonProperty("Password")
  private String password;
}
