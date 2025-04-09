package org.sam.lab;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Impl.JobHistoryRepositoryImp;
import org.sam.lab.repositorio.Repositorio;

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


public class EjemploDBConnectionClose {
    public static void main(String[] args) throws ParseException {

            //Repositorio<Jobs, String> trabajosRepositorio = new JobsRepositorioImpl();

            // Obtener una fila por Id
            //System.out.println("\r\n################ porID ################\r\n");
            //Jobs trabajo = trabajosRepositorio.porId("HR_REP");
            //printObjeto(trabajo);

            //Update
            //System.out.println("\r\n################ Update ################\r\n");
            //trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000);
            //trabajosRepositorio.guardar(trabajo);
            //printObjeto(trabajosRepositorio.porId("HR_REP"));

            // Insert
            //System.out.println("\r\n################ Insert ################\r\n");
            //trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900);
            //trabajosRepositorio.guardar(trabajo);
            //printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

            // Eliminar
            //trabajosRepositorio.eliminar("IT_PROG_JR");

            //System.out.println("\r\n################ Listado Completo ################\r\n");

            // Obtener una lista
            //trabajosRepositorio.listar().forEach(Main::printObjeto);

            Repositorio<JobHistory, Integer> historicoRepositorio = new JobHistoryRepositoryImp();

            // Obtener una fila por Id
            System.out.println("\r\n################ porID ################\r\n");
            JobHistory historico = historicoRepositorio.porId(102);
            printObjeto(historico);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String dateInStartDate = "13/01/01";
            String dateInEndDate = "24/07/06";

            //Update
            java.util.Date startDate = formatter.parse(dateInStartDate);
            java.util.Date endDate = formatter.parse(dateInEndDate);

            System.out.println("\r\n################ Update ################\r\n");
            historico = new JobHistory(102, startDate, endDate, new Jobs("IT_PROG", null, null, null), 70);
            historicoRepositorio.guardar(historico);
            printObjeto(historicoRepositorio.porId(102));

            // Insert
            System.out.println("\r\n################ Insert ################\r\n");
            historico = new JobHistory(108, startDate, endDate, new Jobs("IT_PROG", null, null, null), 60);
            historicoRepositorio.guardar(historico);
            printObjeto(historicoRepositorio.porId(108));

            // Eliminar
            System.out.println("\r\n################ Eliminar ################\r\n");
            System.out.println("EMPLOYEE_ID: " + 108);
            historicoRepositorio.eliminar(108);

            System.out.println("\r\n################ Listado Completo ################\r\n");
            List<JobHistory> listaOrdenada = historicoRepositorio.listar();
            listaOrdenada.sort(Comparator.comparing(JobHistory::getEmployeeId));
            listaOrdenada.forEach(EjemploDBConnectionClose::printObjeto);

        /*
            📌 En Resumen
            ✅ Usa `DriverManager` solo para pruebas o apps pequeñas.
            ✅ Usa `DataSource` si tu aplicación necesita reutilizar conexiones y mejorar el rendimiento.
            ✅ Si usas Spring Boot, Jakarta EE o un servidor como Tomcat, `DataSource` es la mejor opción.

            🚀 Si estás haciendo algo serio con Java y Oracle, usa `DataSource`.🔥😎
        */
    }

    public static void printObjeto(Object objeto){
        System.out.println(objeto.toString());
    }

}