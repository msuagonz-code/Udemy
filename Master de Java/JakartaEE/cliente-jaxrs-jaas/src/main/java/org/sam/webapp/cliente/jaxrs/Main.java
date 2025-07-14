package org.sam.webapp.cliente.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.internal.BasicAuthentication;
import org.sam.webapp.cliente.jaxrs.models.Curso;
import org.sam.webapp.cliente.jaxrs.models.Instructor;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client cliente = ClientBuilder.newClient();
        WebTarget rootUri = cliente.target("http://127.0.0.1:8080/webapp-jaxrs-jaas/api").path("/cursos");

        rootUri.register(new BasicAuthentication("admin", "123456"));

        System.out.println("====================== Por id ======================");
        Response response = rootUri.path("/2").request(MediaType.APPLICATION_JSON).get();

        Curso curso = response.readEntity(Curso.class);

        System.out.println("Status: "+ response.getStatus());
        System.out.println("MediaType: "+ response.getMediaType());
        System.out.println(curso);

        System.out.println("====================== Listando ======================");
        listar(rootUri);

        System.out.println("====================== Creando ======================");
        Curso cursoNuevo = new Curso();
        cursoNuevo.setNombre("Spring Cloud");
        cursoNuevo.setDescripcion("Spring Cloud Microservicios");
        cursoNuevo.setDuracion(30D);
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        instructor.setNombre("Andres");
        instructor.setApellido("Guzman");
        cursoNuevo.setInstructor(instructor);

        Entity<Curso> entityHeader = Entity.entity(cursoNuevo, MediaType.APPLICATION_JSON);
        curso = rootUri.request(MediaType.APPLICATION_JSON).post(entityHeader, Curso.class);

        System.out.println("Curso :"+ curso);

        System.out.println("====================== Editando ======================");

        Curso cursoEditado = curso;
        cursoEditado.setNombre("Microservicios con Spring Cloud");
        entityHeader = Entity.entity(cursoEditado, MediaType.APPLICATION_JSON);
        curso = rootUri.path("/"+curso.getId()).request(MediaType.APPLICATION_JSON).put(entityHeader, Curso.class);
        System.out.println("Curso: "+ curso);
        listar(rootUri);


        System.out.println("====================== Eliminando ======================");
        rootUri.path("/"+curso.getId()).request().delete();
        listar(rootUri);
    }

    private static void listar(WebTarget rootUri) {
        Response response = rootUri.request(MediaType.APPLICATION_JSON)
                .get();

        List<Curso> cursos = response.readEntity(new GenericType<List<Curso>>(){});

        System.out.println("Status: "+ response.getStatus());
        System.out.println("MediaType: "+ response.getMediaType());
        cursos.forEach(System.out::println);
    }
}
