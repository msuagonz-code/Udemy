package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        List<Cliente> clientes = entityManager.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle", Cliente.class).getResultList();

        clientes.forEach(cliente -> {
            System.out.println(cliente.getNombre() +", direcciones: "+ cliente.getDirecciones());
        });
        entityManager.close();
    }
}
