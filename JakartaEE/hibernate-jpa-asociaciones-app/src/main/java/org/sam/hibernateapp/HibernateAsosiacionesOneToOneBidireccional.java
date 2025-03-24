package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.ClienteDetalle;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsosiacionesOneToOneBidireccional {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try{
            entityManager.getTransaction().begin();

                Cliente cliente = new Cliente("cata", "edu");
                cliente.setFormaPago("paypal");

                ClienteDetalle detalle = new ClienteDetalle(true, 8000L);

                cliente.addDetalle(detalle);

            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            System.out.println(cliente);
        } catch (Exception e) {
            entityManager.getTransaction().commit();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
