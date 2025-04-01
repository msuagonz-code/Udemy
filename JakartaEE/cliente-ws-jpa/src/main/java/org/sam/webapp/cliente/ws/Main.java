package org.sam.webapp.cliente.ws;

import org.sam.webapp.jaxws.services.Curso;
import org.sam.webapp.jaxws.services.impl.CursoServicioWs;
import org.sam.webapp.jaxws.services.impl.CursoServicioWsImplService;

public class Main {
    public static void main(String[] args) {
        CursoServicioWs service = new CursoServicioWsImplService().getCursoServicioWsImplPort();

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