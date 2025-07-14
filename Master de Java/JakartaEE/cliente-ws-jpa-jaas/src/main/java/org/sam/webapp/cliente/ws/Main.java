package org.sam.webapp.cliente.ws;

import jakarta.xml.ws.BindingProvider;
import org.sam.webapp.jaxws.services.Curso;
import org.sam.webapp.jaxws.services.impl.CursoServicioWs;
import org.sam.webapp.jaxws.services.impl.CursoServicioWsImplService;

public class Main {
    public static void main(String[] args) {
        CursoServicioWs service = new CursoServicioWsImplService().getCursoServicioWsImplPort();

        ((BindingProvider)service).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
        ((BindingProvider)service).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "123456");

        Curso curso = new Curso();
        curso.setNombre("React");
        curso.setInstructor("Manuel Suárez");
        curso.setDescripcion("React Js de 0 a 100");
        curso.setDuracion(80D);

        Curso respuesta = service.guardar(curso);

        System.out.println("Nuevo curso: "+ respuesta.getNombre());

        service.listar().forEach(c ->{
            System.out.println(c.getNombre());
        });

    }
}