package org.sam.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.sam.hibernateapp.entity.Cliente;
import org.sam.hibernateapp.util.JpaUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        // Este objeto es compartido
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();

        System.out.println("==================== Consultar todos ====================");
        // Siempre se crea un nuevo query con criteria.createQuery(Clase.class) y un from
        // el nuevo from se crea con query.from(Clase.class)
        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);

        List<Cliente> clientes = entityManager.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Listar con where equals ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));

        clientes = entityManager.createQuery(query).setParameter("nombre", "Andres").getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Usando where like para buscar clientes por nombre ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // String de busqueda
        String strParam = "aNd";

        // Definir el parámetro de búsqueda
        ParameterExpression<String> parametro = criteria.parameter(String.class, "param");

        // Usar la función LOWER para hacer la búsqueda insensible a mayúsculas y minúsculas
        query.select(from).where(criteria.like(criteria.lower(from.get("nombre")), criteria.lower(parametro)));

        // Establecer el parámetro y ejecutar la consulta
        clientes = entityManager.createQuery(query)
                .setParameter("param", "%"+strParam+"%")
                .getResultList();

        clientes.forEach(System.out::println);

        System.out.println("==================== Usando where y between para rangos ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        clientes = entityManager.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta where in ====================");
        List<String> parametroArray = Arrays.asList("Stefany", "John", "Manuel");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");
        query.select(from).where(from.get("nombre").in(listParam));

        clientes = entityManager.createQuery(query)
                .setParameter("nombres", parametroArray)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== filtrar usando predicados mayor que o mayor igual ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        // criteria.ge -> mayor o i gual: >=
        // criteria.gt -> mayor que: >
        // criteria.le -> menor o igual: <=
        // criteria.lt -> menor que: <
        query.select(from).where(criteria.lt(criteria.length(from.get("nombre")), 7));

        clientes = entityManager.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta con predicados conjunción and y disyunción or ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago = criteria.equal(from.get("formaPago"), "Debito");
        Predicate porid = criteria.gt(from.get("id"), 6);
        // criteria.or, criteria.and
        //query.select(from).where(criteria.or(porNombre, porFormaPago));
        query.select(from).where(criteria.and(porid, criteria.or(porNombre, porFormaPago)));

        clientes = entityManager.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta con Order by asc desc ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.desc(from.get("nombre")), criteria.asc(from.get("apellido")));

        clientes = entityManager.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==================== Consulta por id ====================");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Long id = 3l;
        ParameterExpression<Long> idPram = criteria.parameter(Long.class, "id");

        query.select(from).where(criteria.equal(from.get("id"), idPram));
        Cliente cliente = entityManager.createQuery(query).setParameter("id", id).getSingleResult();

        System.out.println("Cliente: " + cliente);

        System.out.println("==================== Consulta solo el nombre de los clientes ====================");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(from.get("nombre"));
        List<String> nombres = entityManager.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta solo el nombre de los clientes unicos con distinct ====================");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);
        nombres = entityManager.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta por nombre y apellido de los clientes concatenado ====================");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido")));

        nombres = entityManager.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta por nombre y apellido de los clientes concatenado upper o lower ====================");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        //queryString.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));
        queryString.select(criteria.lower(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));

        nombres = entityManager.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("==================== Consulta de campos personalizados del entity cliente ====================");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        List<Object[]> registros = entityManager.createQuery(queryObject).getResultList();

        registros.forEach(reg ->{
            Long idReg = (Long) reg[0];
            String nombreReg = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id: " + idReg +", nombre: "+ nombreReg +", apellido: "+apellido);
        });

        System.out.println("==================== Consulta de campos personalizados del entity cliente where ====================");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        // Definir el parámetro de búsqueda
        strParam = "lu";
        ParameterExpression<String> parametro2 = criteria.parameter(String.class, "param");

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.like(criteria.lower(from.get("nombre")), criteria.lower(parametro2)));

        registros = entityManager.createQuery(queryObject)
                .setParameter("param", "%"+strParam+"%")
                .getResultList();

        registros.forEach(reg ->{
            Long idReg = (Long) reg[0];
            String nombreReg = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id: " + idReg +", nombre: "+ nombreReg +", apellido: "+apellido);
        });

        System.out.println("==================== Consulta de campos personalizados del entity cliente where id ====================");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        // Definir el parámetro de búsqueda
        Long longParam = 3L;
        ParameterExpression<Long> parametro3 = criteria.parameter(Long.class, "param");

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.equal(from.get("id"), parametro3));

        Object[] registro = entityManager.createQuery(queryObject)
                .setParameter("param", longParam)
                .getSingleResult();

        Long idReg = (Long) registro[0];
        String nombreReg = (String) registro[1];
        String apellido = (String) registro[2];
        System.out.println("id: " + idReg +", nombre: "+ nombreReg +", apellido: "+apellido);

        System.out.println("==================== Contar registros de la consulta con count ====================");
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.count(from.get("id")));

        Long count = entityManager.createQuery(queryLong).getSingleResult();
        System.out.println("Cantidad de registros: " + count);

        System.out.println("==================== sumar datos de algun campo de la tabla ====================");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.sum(from.get("id")));
        Long sum = entityManager.createQuery(queryLong).getSingleResult();

        System.out.println("Suma de los id: " + sum);

        System.out.println("==================== Consulta con el maximo id ====================");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.max(from.get("id")));

        Long max = entityManager.createQuery(queryLong).getSingleResult();
        System.out.println("ID maximo: " + max);

        System.out.println("==================== Consulta con el minimo id ====================");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteria.min(from.get("id")));

        Long min = entityManager.createQuery(queryLong).getSingleResult();
        System.out.println("ID minimo: " + min);

        System.out.println("==================== Varios resultados de agregación con multiselect ====================");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(criteria.count(from.get("id"))
                , criteria.sum(from.get("id"))
                , criteria.max(from.get("id"))
                , criteria.min(from.get("id")));

        registro = entityManager.createQuery(queryObject).getSingleResult();

        count = (Long) registro[0];
        sum = (Long) registro[1];
        max = (Long) registro[2];
        min = (Long) registro[3];

        System.out.println("Count: " + count+ ", Sum: "+ sum +", Max: "+ max +", Min:"+ min);

        entityManager.close();
    }
}
