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
    public static void main(String[] args) throws SQLException {

        try(Connection conn = ConexionBaseDatos.getConnectionInstance()) {

            if(conn.getAutoCommit()){
                // Se setea el auto commit a false para poder hacer rollback
                conn.setAutoCommit(false);
            }

            try {
                Repositorio<Jobs, String> trabajosRepositorio = new JobsRepositorioImpl();

                // Obtener una fila por Id
                System.out.println("\r\n################ porID ################\r\n");
                Jobs trabajo = trabajosRepositorio.porId("HR_REP");
                printObjeto(trabajo);

                /*
                // Rompe la restricción de valor unico en la tabla, para hacer rollback
                String skuDuplicado = "abcd1234";

                // Insert
                System.out.println("\r\n################ Insert ################\r\n");
                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, skuDuplicado);
                trabajosRepositorio.guardar(trabajo);
                printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

                //Update
                System.out.println("\r\n################ Update ################\r\n");
                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, skuDuplicado);
                trabajosRepositorio.guardar(trabajo);
                printObjeto(trabajosRepositorio.porId("HR_REP"));
                */

                /*
                * Este bloque no viola la restrucción del SKU
                * */
                // Insert
                System.out.println("\r\n################ Insert ################\r\n");
                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, "abcd1234");
                trabajosRepositorio.guardar(trabajo);
                printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

                //Update
                System.out.println("\r\n################ Update ################\r\n");
                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, null);
                trabajosRepositorio.guardar(trabajo);
                printObjeto(trabajosRepositorio.porId("HR_REP"));

                // Eliminar
                trabajosRepositorio.eliminar("IT_PROG_JR");

                // Obtener una lista
                System.out.println("\r\n################ Listado Completo ################\r\n");
                List<Jobs> listaTrabajo = trabajosRepositorio.listar();
                listaTrabajo.forEach(EjemploDBConnectionSingletonTrx::printObjeto);

                conn.commit(); //Al finalizar las operacione se hace el commit
            } catch (SQLException e) {
                conn.rollback(); // Si ha ocurrido un error se hace rollback
                e.printStackTrace();
            }
        } // Aquí se cierra la conexion con el try con recursos

    }

    public static void printObjeto(Object objeto){
        System.out.println(objeto.toString());
    }

}