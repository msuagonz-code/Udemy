package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.Factura;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente("Goku", "Guzmán");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("Compra de supermercado", 5000L);
            Factura f2 = new Factura("Compra de tecnología", 7000L);

            cliente.addFactura(f1).addFactura(f2);

            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
            System.out.println(cliente);

            entityManager.getTransaction().begin();
            //Factura f3 = entityManager.find(Factura.class, 1L);
            Factura f3 = new Factura("Compra de supermercado", 5000L);
            f3.setId(1L);
            cliente.removeFactura(f3);
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
