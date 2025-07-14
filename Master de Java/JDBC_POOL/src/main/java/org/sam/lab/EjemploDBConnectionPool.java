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


public class EjemploDBConnectionPool {
    public static void main(String[] args) throws ParseException {

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
            listaOrdenada.forEach(EjemploDBConnectionPool::printObjeto);
            
    }

    public static void printObjeto(Object objeto){
        System.out.println(objeto.toString());
    }

}