package com.ontology.configuration;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build();
  }
  
  @Bean
  public EhCacheManagerFactoryBean cacheMangerFactory() {
      EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
      bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
      bean.setShared(true);
      return bean;
  }

}
