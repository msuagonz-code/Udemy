package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        Cliente cliente = entityManager.find(Cliente.class, 1L);
        System.out.println(cliente.getNombre() + ", Direcciones: " + cliente.getDirecciones());
        entityManager.close();
    }
}
