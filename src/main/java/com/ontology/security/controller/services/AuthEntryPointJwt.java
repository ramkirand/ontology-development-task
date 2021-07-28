package com.ontology.security.controller.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
  
  private static final String ERROR_UNAUTHORIZED = "Error: Unauthorized";

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException authException) throws IOException, ServletException {
      log.error("Unauthorized error: {}", authException.getMessage());
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ERROR_UNAUTHORIZED);
  }
}
