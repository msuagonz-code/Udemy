package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateFind {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese el Id: ");
        Long id = s.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        //Se hace la consulta una sola vez, y el objeto resultado se guarda
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        // Si es el mismo ID, No se hace la consulta a la DB, se usa el objeto guardado en cache
        Cliente cliente2 = em.find(Cliente.class, 2L);
        System.out.println(cliente2);

        em.close();
    }
}
