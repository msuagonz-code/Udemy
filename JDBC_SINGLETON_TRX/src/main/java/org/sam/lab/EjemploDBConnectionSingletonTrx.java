package org.sam.lab;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Impl.JobHistoryRepositoryImp;
import org.sam.lab.repositorio.Impl.JobsRepositorioImpl;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/*
 * https://www.oracle.com/es/database/technologies/faq-jdbc.html
 * https://github.com/brettwooldridge/HikariCP
 * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
 * */

public class EjemploDBConnectionSingletonTrx {
    public static void main(String[] args) {

        try(Connection con = ConexionBaseDatos.getConnectionInstance()) {
            Repositorio<Jobs, String> trabajosRepositorio = new JobsRepositorioImpl();

            // Obtener una fila por Id
            System.out.println("\r\n################ porID ################\r\n");
            Jobs trabajo = trabajosRepositorio.porId("HR_REP");
            printObjeto(trabajo);

            // Insert
            System.out.println("\r\n################ Insert ################\r\n");
            trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, "abcd1234");
            trabajosRepositorio.guardar(trabajo);
            printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

            //Update
            System.out.println("\r\n################ Update ################\r\n");
            trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, "abcd1234");
            trabajosRepositorio.guardar(trabajo);
            printObjeto(trabajosRepositorio.porId("HR_REP"));


            // Eliminar
            //trabajosRepositorio.eliminar("IT_PROG_JR");


            // Obtener una lista
            System.out.println("\r\n################ Listado Completo ################\r\n");
            List<Jobs> listaTrabajo= trabajosRepositorio.listar();
            listaTrabajo.forEach(EjemploDBConnectionSingletonTrx::printObjeto);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void printObjeto(Object objeto){
        System.out.println(objeto.toString());
    }

}