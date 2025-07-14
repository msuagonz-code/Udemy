package org.sam.webapp.jaxws.services.impl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.sam.webapp.jaxws.models.Curso;
import org.sam.webapp.jaxws.services.ServicioWs;

import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "org.sam.webapp.jaxws.services.ServicioWs")
public class ServicioWsImpl implements ServicioWs {

    private int contador;

    @Override
    @WebMethod
    public String saludar(String persona) {
        System.out.println("Imprimiendo dentro del servicio web con instancia: "+  this);
        contador++;
        System.out.println("Valor del contador en el metodo saludar: "+ contador);
        return "Hola que tal "+ persona;
    }

    @Override
    @WebMethod
    public List<Curso> listar() {
        return Arrays.asList(new Curso("Java"), new Curso("Spring"), new Curso("Maquillaje cuántico"));
    }

    @Override
    @WebMethod
    public Curso crear(Curso curso) {
        System.out.println("Curso guardado con éxito ..."+  curso.getNombre());
        return new Curso(curso.getNombre());
    }
}


