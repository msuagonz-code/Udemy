package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaBusquedaDinamica {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Filtro por nombre: ");
        String nombre = s.nextLine();

        System.out.print("Filtro por apellido: ");
        String apellido = s.nextLine();

        System.out.print("Filtro por forma de pago: ");
        String formaPago = s.nextLine();

        EntityManager entityManager = JpaUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate> condiciones = new ArrayList<>();
        if(nombre != null && !nombre.isEmpty()){
            condiciones.add(criteriaBuilder.equal(from.get("nombre"), nombre));
        }

        if(apellido != null && !apellido.isEmpty()){
            condiciones.add(criteriaBuilder.equal(from.get("apellido"), apellido));
        }

        if(formaPago != null && !formaPago.isEmpty()){
            condiciones.add(criteriaBuilder.equal(from.get("formaPago"), formaPago));
        }

        query.select(from).where(criteriaBuilder.and(condiciones.toArray(new Predicate[condiciones.size()])));
        List<Cliente> clienteList = entityManager.createQuery(query).getResultList();

        clienteList.forEach(System.out::println);

        entityManager.close();
    }
}
