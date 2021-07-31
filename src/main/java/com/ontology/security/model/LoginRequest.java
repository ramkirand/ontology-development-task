package com.ontology.security.model;

import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "USER")
public class LoginRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
