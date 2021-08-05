package com.ontology.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ToString

@ApiModel(value = "User details")
@Data
@Document(collection = "USERS")
public class AppUser implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  @JsonProperty("Username")
  private String username;

  @JsonProperty("Password")
  private String password;

  @NotBlank
  @Size(max = 20)
  @JsonProperty("Name")
  private String name;

  @NotBlank
  @Size(max = 50)
  @JsonProperty("Email")
  private String email;

  // @DBRef
  // private Set<Role> roles = new HashSet<>();
 
 // @JsonProperty("Role")
  private String role; 

  public AppUser() {}

  public AppUser(@NotBlank @Size(max = 20) String username, String name,
      @NotBlank @Size(max = 20) String password, String role) {

    this.username = username;
    this.password = password;
    this.name = name;
    this.role = role;
  }
}
