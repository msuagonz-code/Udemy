package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.Factura;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToOne {

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "edu");
            cliente.setFormaPago("Credito");

            entityManager.persist(cliente);

            Factura factura = new Factura("Compras de oficina", 1000L);
            factura.setCliente(cliente);

            entityManager.persist(factura);

            System.out.println("Factura: "+ factura);
            entityManager.getTransaction().commit();
        }catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }


    }
}
