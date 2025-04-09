package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);

        System.out.print("Ingrese el Id: ");
        Long pago = s.nextLong();

        query.setParameter(1, pago);

        Cliente c = (Cliente) query.getSingleResult();

        System.out.println(c);

        em.close();
    }
}
