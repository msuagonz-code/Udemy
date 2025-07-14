package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Alumno;
import org.sam.hibernateapp.entity.Curso;
import org.sam.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try{
            entityManager.getTransaction().begin();

            Alumno alumno1 = new Alumno("Goku", "Rodriguez");
            Alumno alumno2 = new Alumno("Vegueta", "Martinez");

            Curso curso1 = new Curso("Andres", "curso Java");
            Curso curso2 = new Curso("Andres", "curso Springboot");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            entityManager.persist(alumno1);
            entityManager.persist(alumno2);

            entityManager.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

            System.out.println("============================== Eliminar ==============================");
            entityManager.getTransaction().begin();
            Curso c2 = entityManager.find(Curso.class, 3L);
            alumno1.getCursos().remove(c2);

            entityManager.getTransaction().commit();
            System.out.println(alumno1);

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }
}
