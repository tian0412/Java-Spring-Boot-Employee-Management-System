package com.aopLogging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogging {

    Logger log = (Logger) LoggerFactory.getLogger(AuditLogging.class);

    //       match any return type,        any class, any method, any no of parameters
    @Pointcut(value = " execution(* com.controller..*(..) ) || execution(* com.service..*(..) ) || execution(* com.repository..*(..) )")
    public void myPointCut(){}

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();

        // Each log entry should capture the user's action, the affected entity, and the timestamp.
        log.info("method invoked: " + className + " : " + methodName + "() -> " + "arguments : " +
                mapper.writeValueAsString(array));

        Object response = pjp.proceed();
        Object responseBody = null;
        if (response instanceof ResponseEntity<?> responseEntity){
            responseBody = responseEntity.getBody();
        } else {
            responseBody = response;
        }

        log.info(className + " : " + methodName + "()" + " -> Response : " +
                mapper.writeValueAsString(responseBody));
        return response;
    }
}
