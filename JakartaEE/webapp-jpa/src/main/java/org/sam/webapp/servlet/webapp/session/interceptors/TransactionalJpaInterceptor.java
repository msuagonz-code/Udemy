package org.sam.webapp.servlet.webapp.session.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.sam.webapp.servlet.webapp.session.configs.OracleConn;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;

import java.util.logging.Logger;

@TransactionalJPA
@Interceptor
public class TransactionalJpaInterceptor {

     @Inject
     @OracleConn
     private EntityManager entityManager;

     @Inject
     private Logger log;

     @AroundInvoke
     public Object transactional(InvocationContext invocationContext) throws Exception {

         try{
         log.info(" ------> Iniciando Transacción: "+ invocationContext.getMethod().getName() +
                 " de la clase "+ invocationContext.getMethod().getDeclaringClass());

            entityManager.getTransaction().begin();

            Object resultado = invocationContext.proceed();
            
            entityManager.getTransaction().commit();

             log.info(" ------> Finalizando Transacción: "+ invocationContext.getMethod().getName() +
                     " de la clase "+ invocationContext.getMethod().getDeclaringClass());

            return resultado;
         } catch (ServiceJDBCException e) {
             entityManager.getTransaction().rollback();
             throw e;
         }
     }
}
