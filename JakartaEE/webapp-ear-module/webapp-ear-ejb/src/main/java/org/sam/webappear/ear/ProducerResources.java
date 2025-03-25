package org.sam.webappear.ear;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class ProducerResources {

    @PersistenceUnit(name = "ejemploJPA")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            System.out.println("Cerrando la conexion del entity manager...");
        }
    }
}
