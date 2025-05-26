package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Alumno;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        List<Alumno> alumnos = entityManager.createQuery("select distinct a from Alumno a left outer join fetch a.cursos", Alumno.class).getResultList();
        alumnos.forEach(alumno -> {
            System.out.println( alumno.getNombre() + ", Cursos: "+ alumno.getCursos());
        });
        entityManager.close();
    }
}
