package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Alumno;
import org.sam.hibernateapp.entity.Curso;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyFind {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try{
            entityManager.getTransaction().begin();

            Alumno alumno1 = entityManager.find(Alumno.class, 1L);
            Alumno alumno2 = entityManager.find(Alumno.class, 2L);

            //Curso curso1 = new Curso("LatinCrack", "curso Hacking");
            //Curso curso2 = new Curso("Victor", "curso Springboot");
            Curso curso1 = entityManager.find(Curso.class, 1L);
            Curso curso2 = entityManager.find(Curso.class, 2L);

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            entityManager.persist(alumno1);
            entityManager.persist(alumno2);

            entityManager.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
