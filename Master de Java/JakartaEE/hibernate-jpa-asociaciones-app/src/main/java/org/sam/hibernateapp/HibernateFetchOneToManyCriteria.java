package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        from.fetch("direcciones", JoinType.LEFT);
        from.fetch("detalle", JoinType.LEFT);
        query.select(from).distinct(true);

        List<Cliente> clientes = entityManager.createQuery(query).getResultList();

        clientes.forEach(cliente -> {
            System.out.println(cliente.getNombre() +", detalle: "+ cliente.getDetalle() +", direcciones: "+ cliente.getDirecciones());
        });

        entityManager.close();
    }
}
