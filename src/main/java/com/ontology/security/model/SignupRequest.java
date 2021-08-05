package com.ontology.security.model;

import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "USERS")
public class SignupRequest{
 
//  @JsonProperty("Email")
  private String username;
  // @Size(min = 3, max = 20)
//  @JsonProperty("Password")
  private String password;

//  @JsonProperty("Name")
  private String name;
  // @Size(min = 6, max = 40)

//  @JsonProperty("roles")
  private String role;
}
