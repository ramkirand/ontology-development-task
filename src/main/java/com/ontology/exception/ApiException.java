package com.ontology.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException {
  @JsonProperty("Reason")
  private String message;
  private HttpStatus httpStatus;
  private ZonedDateTime timeStamp;
}
