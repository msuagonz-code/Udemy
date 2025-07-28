package org.sam.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    private void greetingFooLoogerPointCut(){}

    @Before("greetingFooLoogerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes primero: "+ method + " Invocado con los parametros: "+ args);

    }

    /* Se ejecuta sin importar si hay o no errores */
    @After("greetingFooLoogerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues primero: "+ method + " Invocado con los parametros: "+ args);

    }

}
