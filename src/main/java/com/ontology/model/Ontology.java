package com.ontology.model;

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
public class Ontology {
  @Id
  private String ontologyId;
  private String title;
  private String description;
  private String synonymProperties;
  private String definitionProperties;
}
