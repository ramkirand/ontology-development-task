package com.ontology.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class PerformanceMonitoringAdvice {
  @Around("@annotation(com.ontology.annotation.TrackExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long executionTime = System.currentTimeMillis() - start;
    log.info("Logging Method Name: " + joinPoint.getSignature()
        + " ... time taken to execute in milliseconds: " + executionTime);
    return proceed;
  }

}
