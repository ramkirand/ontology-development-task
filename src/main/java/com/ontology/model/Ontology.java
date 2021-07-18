package com.ontology.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor
@ApiModel(value = "Ontology details")
@Builder
@Getter
@AllArgsConstructor
@Document(collection = "ONTOLOGY")
public class Ontology implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String ontologyId;
  private String title;
  private String description;
  private String synonymProperties;
  private String definitionProperties;
}
