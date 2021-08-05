package com.ontology.security.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class JwtResponse implements Serializable{
  private static final long serialVersionUID = 1L;
  private String token;
  private String type = "Bearer";
  private String id;
  private String username;
  private String email;
  private String name;
  //private List<String> roles;
  private String role;



  public JwtResponse(String accessToken, String id, String username, String email,
      String role) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
  }



}


