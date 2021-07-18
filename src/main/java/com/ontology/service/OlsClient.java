package com.ontology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.ontology.annotation.TrackExecutionTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OlsClient {

  @Value("${HTTPS_WWW_EBI_AC_UK}")
  private String ebiUrl;
  private RestTemplate restTemplate;

  @Autowired
  public OlsClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @TrackExecutionTime
  public ResponseEntity<String> retrieveOntologyInformation(String url) {
    ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
    log.info("response from OLS :" + result);
    return result;
  }
}
