package org.sam.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: "+ method + " con los argumentos: "+ args);

    }

    /* Se ejecuta sin importar si hay o no errores */
    @After("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: "+ method + " con los argumentos: "+ args);

    }

    /* Solo se ejecuta si no hay errores */
    @AfterReturning("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de retornar: "+ method + " con los argumentos: "+ args);

    }

    @AfterThrowing("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar la excepcion: "+ method + " con los argumentos: "+ args);

    }

    @Around("execution(* org.sam.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {

            logger.info("El metoddo " + method + "() con los parametros "+ args);
            result = joinPoint.proceed();
            logger.info("El metodo "+ method +"() retorna el resultado: "+ result);

            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del metodo "+ method +"()");
            e.printStackTrace();
            throw e;
        }

    }
}
