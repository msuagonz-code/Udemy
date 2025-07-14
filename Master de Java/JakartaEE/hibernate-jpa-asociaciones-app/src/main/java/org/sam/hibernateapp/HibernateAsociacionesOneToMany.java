package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.Direccion;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try{
            System.out.println("==================== Insert ====================");
            entityManager.getTransaction().begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("mercado negro");

            Direccion d1 = new Direccion("urb. La pradera", 30100);
            Direccion d2 = new Direccion("Playa la rosa", 30110);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            System.out.println(cliente);

            System.out.println("==================== remove ====================");
            entityManager.getTransaction().begin();

            cliente = entityManager.find(Cliente.class, cliente.getId());
            cliente.getDirecciones().remove(d1);

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
