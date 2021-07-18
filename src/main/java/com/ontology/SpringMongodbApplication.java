package com.ontology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class SpringMongodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMongodbApplication.class, args);
  }
}
