package com.ontology.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ontology.exception.ApiRequestException;
import com.ontology.model.Ontology;
import com.ontology.service.OntologyService;
import com.ontology.user.OntologyUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class OntologyController {
  @Autowired
  OntologyService ontologyService;
  @Autowired
  OntologyUserService userService;
  @Value("${FAILED_TO_GET_ONTOLOGY_INFORMATION_FROM_OLS}")
  private String errorInfo;

  @ApiOperation(value = "Get an Onology by ontologyId", response = Ontology.class)
  @GetMapping("ols/api/ontologies/{ontologyId}")
  public Optional<Ontology> getOntologyById(@PathVariable String ontologyId) throws IOException {
    try {
      log.info("Ontology by Id:" + ontologyService.getOntologyById(ontologyId));
      return ontologyService.getOntologyById(ontologyId);
    } catch (ApiRequestException ex) {
      throw new ApiRequestException(errorInfo + ex.getLocalizedMessage());
    }
  }


  @ApiOperation(value = "Get all Ontologies", response = List.class)
  @GetMapping("ols/api/ontologies")
  public List<Ontology> getOntologies() {
    try {
      return ontologyService.getOntologies();
    } catch (ApiRequestException ex) {
      throw new ApiRequestException(errorInfo + ex.getLocalizedMessage());
    }
  }

  
}
