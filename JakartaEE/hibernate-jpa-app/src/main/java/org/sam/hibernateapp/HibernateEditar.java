package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try{

            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a modificar:"));
            Cliente c = em.find(Cliente.class, id);

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido: ", c.getApellido());
            String pago = JOptionPane.showInputDialog("Ingrese el forma de pago: ", c.getFormaPago());

            // Iniciamos una nueva transacción
            em.getTransaction().begin();

            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);

            em.merge(c);
            em.getTransaction().commit();

            System.out.println(c);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
