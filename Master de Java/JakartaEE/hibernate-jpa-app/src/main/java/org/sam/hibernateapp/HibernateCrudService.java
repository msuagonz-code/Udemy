package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.services.ClienteService;
import org.sam.hibernateapp.services.Impl.ClienteServiceImpl;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("========== Listar ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("========== Obtener por Id ==========");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("========== Insertar Nuevo Cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Cata");
        cliente.setApellido("García");
        cliente.setFormaPago("Credito");

        service.guardar(cliente);
        System.out.println("Cliente guardado con éxito");

        service.listar().forEach(System.out::println);

        System.out.println("========== Editar Cliente ==========");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("Paypal");
            service.guardar(c);
            System.out.println("Cliente Editado con éxito");
            service.listar().forEach(System.out::println);
        });

        System.out.println("========== Eliminar Cliente ==========");
        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Cliente Eliminado con éxito");
            service.listar().forEach(System.out::println);
        });
        /*
        if(optionalCliente.isPresent()){
            service.eliminar(id);
        }
        */

        em.close();
    }
}
