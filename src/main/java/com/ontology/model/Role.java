package com.ontology.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ROLES")
@Data
@NoArgsConstructor
public class Role {
  @Id
  private String id;

//  private ERole name;
  private String name;

//  public Role(ERole name) {
//    this.name = name;
//  }

}
