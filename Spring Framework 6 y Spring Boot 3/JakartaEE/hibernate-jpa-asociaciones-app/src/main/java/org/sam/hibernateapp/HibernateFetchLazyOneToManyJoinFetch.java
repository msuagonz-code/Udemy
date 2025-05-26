package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        Cliente cliente = entityManager.createQuery("select c from Cliente c left outer join fetch c.direcciones left join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente.getNombre() + ", Direcciones: " + cliente.getDirecciones());
        System.out.println(cliente.getNombre() + ", Detalle: " + cliente.getDetalle());
        entityManager.close();
    }
}
