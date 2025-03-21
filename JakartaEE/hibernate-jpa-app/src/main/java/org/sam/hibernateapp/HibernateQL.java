package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import org.sam.hibernateapp.dominio.ClienteDTO;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("==================== Consultar todos ====================");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consultar por ID ====================");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 2L).getSingleResult();
        System.out.println(cliente);

        System.out.println("==================== Consultar Nombre por ID ====================");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                .setParameter("id", 2L).getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("==================== Consultar Campos Personalizados ====================");
        Object[] objectCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L).getSingleResult();

        Long id = (Long) objectCliente[0];
        String nombre = (String) objectCliente[1];
        String apellido = (String) objectCliente[2];
        System.out.print("Id: "+ id);
        System.out.print(", Nombre: "+ nombre);
        System.out.println(", Apellido: "+ apellido);

        System.out.println("==================== Consultar Campos Personalizados lista ====================");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        registros.forEach(objects -> {
            Long idObj = (Long) objects[0];
            String nombreObj = (String) objects[1];
            String apellidoObj = (String) objects[2];
            System.out.print("Id: "+ idObj);
            System.out.print(", Nombre: "+ nombreObj);
            System.out.println(", Apellido: "+ apellidoObj);
        });

        System.out.println("==================== Consultar Campos Personalizados lista ====================");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();

        registros.forEach(reg ->{
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];
            System.out.println("Forma de pago: "+ formaPago +", Cliente: "+ c);
        });

        System.out.println("==================== Consulta que puebla y devuelve objeto entity de una clase personalizada ====================");
        clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta que puebla y devuelve objeto DTO de una clase personalizada ====================");
        List<ClienteDTO> clientesdto = em.createQuery("select new org.sam.hibernateapp.dominio.ClienteDTO(c.nombre, c.apellido) from Cliente c", ClienteDTO.class)
                .getResultList();
        clientesdto.forEach(System.out::println);

        System.out.println("==================== Consulta con nombres de clientes ====================");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta con nombres unicos de clientes ====================");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta con formas de pago unicas ====================");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class).getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("==================== Consulta con numero de formas de pago unicas ====================");
        Long totalformasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class).getSingleResult();
        System.out.println("total forma de pago: "+ totalformasPago);

        System.out.println("==================== Consulta con nombre y apellidos concatenados ====================");
        //nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) from Cliente c", String.class).getResultList();
        nombres = em.createQuery("select c.nombre || ' ' || c.apellido from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta con nombre y apellidos concatenados en mayuscula ====================");
        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta con nombre y apellidos concatenados en minuscula ====================");
        nombres = em.createQuery("select lower(concat(c.nombre, ' ', c.apellido)) from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta para buscar por nombres insensible a mayusculas y minusculas (Oracle) ====================");
        String param = "lU";
        clientes = em.createQuery("select c from Cliente c where lower(c.nombre) like lower(:parametro)", Cliente.class)
                .setParameter("parametro", "%"+param+"%")
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta por rangos ====================");
        //clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'Q'", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta con orden ====================");
        //clientes = em.createQuery("select c from Cliente c order by c.id desc", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c order by c.id asc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta con total de registro ====================");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println("total:"+ total);

        System.out.println("==================== Consulta con un valor minimo del ID ====================");
        Long min = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class).getSingleResult();
        System.out.println("Minimo:"+ min);

        System.out.println("==================== Consulta con un valor máximo del ID ====================");
        Long max = em.createQuery("select max(c.id) as maximo from Cliente c", Long.class).getSingleResult();
        System.out.println("Máximo:"+ max);

        System.out.println("==================== Consulta con nombre y su largo ====================");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        registros.forEach(reg -> {
            String nombreReg = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre: "+ nombreReg + ", Largo:"+ largo);
        });

        System.out.println("==================== Consulta con el nombre más corto ====================");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println("Nombre más corto: "+ minLargoNombre);

        System.out.println("==================== Consulta con el nombre más largo ====================");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println("Nombre más Largo: "+ maxLargoNombre);

        System.out.println("==================== Consulta resumen funciones agregaciones count, min, max, avg, sum ====================");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class).getSingleResult();
        Long estadisticasMin = (Long) estadisticas[0];
        Long estadisticasMax = (Long) estadisticas[1];
        Long estadisticasSum = (Long) estadisticas[2];
        Long estadisticasCount = (Long) estadisticas[3];
        Double estadisticasAvg = (Double) estadisticas[4];

        System.out.print("estadisticasMin: " + estadisticasMin);
        System.out.print(", estadisticasMax: " + estadisticasMax);
        System.out.print(", estadisticasSum: " + estadisticasSum);
        System.out.print(", estadisticasCount: " + estadisticasCount);
        System.out.println(", estadisticasAvg: " + estadisticasAvg);

        System.out.println("==================== Consulta con nombre más corto y su largo ====================");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c " +
                        "where length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                        .getResultList();
        registros.forEach(reg ->{
            String nombreReg = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("Nombre: "+ nombreReg + ", Largo:"+ largo);
        });

        System.out.println("==================== Consulta para obtener el último registro ====================");
        Cliente ultimoRegistro = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                .getSingleResult();
        System.out.println("ultimoRegistro: " + ultimoRegistro);

        System.out.println("==================== Consulta where in ====================");
        clientes = em.createQuery("Select c from Cliente c where c.id in :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L,2L,10L))
                .getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
