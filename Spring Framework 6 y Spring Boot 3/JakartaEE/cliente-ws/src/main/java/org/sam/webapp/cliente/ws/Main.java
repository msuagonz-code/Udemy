package org.sam.webapp.cliente.ws;

import org.sam.webapp.jaxws.services.Curso;
import org.sam.webapp.jaxws.services.impl.ServicioWs;
import org.sam.webapp.jaxws.services.impl.ServicioWsImplService;

public class Main {
    public static void main(String[] args) {
        ServicioWs service = new ServicioWsImplService().getServicioWsImplPort();
        System.out.println("el saludo: "+ service.saludar("Andres"));

        Curso curso = new Curso();
        curso.setNombre("Angular");

        Curso respuesta = service.crear(curso);

        System.out.println("Nuevo curso: "+ respuesta.getNombre());

        service.listar().forEach(c ->{
            System.out.println(c.getNombre());
        });

    }
}