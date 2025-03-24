package org.sam.webapp.servlet.webapp.session.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.sam.webapp.servlet.webapp.session.configs.OracleConn;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJDBC
@Interceptor
public class TransactionalInterceptor {

     @Inject
     @OracleConn
     private Connection conn;

     @Inject
     private Logger log;

     @AroundInvoke
     public Object transactional(InvocationContext invocationContext) throws Exception {
         if(conn.getAutoCommit()){
             conn.setAutoCommit(false);
         }

         try{
         log.info(" ------> Iniciando Transacción: "+ invocationContext.getMethod().getName() +
                 " de la clase "+ invocationContext.getMethod().getDeclaringClass());

            Object resultado = invocationContext.proceed();
            conn.commit();

             log.info(" ------> Finalizando Transacción: "+ invocationContext.getMethod().getName() +
                     " de la clase "+ invocationContext.getMethod().getDeclaringClass());

            return resultado;
         } catch (ServiceJDBCException e) {
             conn.rollback();
             throw e;
         }
     }
}
