package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.ClienteDetalle;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {
        EntityManager entityManager= JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");
            entityManager.persist(cliente);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            entityManager.persist(detalle);
            cliente.setDetalle(detalle);

            entityManager.getTransaction().commit();
            System.out.println(cliente);
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }finally {
            entityManager.close();
        }

    }
}
