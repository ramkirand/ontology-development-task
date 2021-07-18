package com.ontology.api.service;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.ontology.service.OlsClient;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OlsClientTest {
  @Autowired
  private OlsClient olsClient;

  @Value("${HTTPS_WWW_EBI_AC_UK}")
  private String ebiUrl;

  @Test
  public void shouldRetrieveOntologyInformation() {
    ResponseEntity<String> response = olsClient.retrieveOntologyInformation(ebiUrl);
    assertNotNull(response.getBody());
  }
}
