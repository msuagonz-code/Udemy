package org.sam.appcliente.ejb;

import org.sam.appejb.ejb.models.Producto;
import org.sam.appejb.ejb.service.ServiceEjbRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ServiceEjbRemote service =  null;
        ServiceEjbRemote service2 =  null;

        /*
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        env.put("jboss.naming.client.ejb.context", true);
        */

        try {
            InitialContext remoteContext = new InitialContext();
            //service = (ServiceEjbRemote) remoteContext.lookup("ejb:/appejb-remote/ServiceEjb!org.sam.appejb.ejb.service.ServiceEjbRemote");
            service = (ServiceEjbRemote) remoteContext.lookup("ejb:/appejb-remote-jaas/ServiceEjb!org.sam.appejb.ejb.service.ServiceEjbRemote?stateful");
            service2 = (ServiceEjbRemote) remoteContext.lookup("ejb:/appejb-remote-jaas/ServiceEjb!org.sam.appejb.ejb.service.ServiceEjbRemote?stateful");

            String saludo = service.saludar("Manuel");
            String saludo2 = service2.saludar("Stefany");

            System.out.println(saludo);
            System.out.println(saludo2);

            Producto producto = service.crear(new Producto("Sandía"));
            System.out.println("Nuevo producto: " + producto);

            service.listar().forEach(System.out::println);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}