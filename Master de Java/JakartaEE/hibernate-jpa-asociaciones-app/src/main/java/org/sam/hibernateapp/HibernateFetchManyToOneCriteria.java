package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.entity.Factura;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Factura> query = criteriaBuilder.createQuery(Factura.class);
        Root<Factura> from = query.from(Factura.class);
        Join<Factura, Cliente> cliente =(Join) from.fetch("cliente", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);

        query.select(from).where(criteriaBuilder.equal(cliente.get("id"), 1L));
        List<Factura> facturaList = entityManager.createQuery(query).getResultList();
        facturaList.forEach(factura -> {
            System.out.println(factura.getDescripcion() + ", cliente: "+ factura.getCliente().getNombre());
        });
        entityManager.close();
    }
}
