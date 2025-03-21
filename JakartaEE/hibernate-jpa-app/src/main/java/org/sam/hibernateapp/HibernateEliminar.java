package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a Eliminar: ");
        Long id = s.nextLong();

        EntityManager em = JpaUtil.getEntityManager();

        try{
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();

            em.remove(cliente);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }
}
