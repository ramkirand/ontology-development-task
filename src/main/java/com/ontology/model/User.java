package com.ontology.model;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@ApiModel(value = "User details")
@Data
@AllArgsConstructor
@Document(collection = "USER")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;

  private String email;

  private String password;

}
