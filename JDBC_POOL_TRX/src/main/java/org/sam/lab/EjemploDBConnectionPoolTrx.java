package org.sam.lab;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Impl.JobHistoryRepositoryImp;
import org.sam.lab.repositorio.Impl.JobsRepositorioImpl;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.servicio.RRHHServicio;
import org.sam.lab.servicio.Servicio;
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

public class EjemploDBConnectionPoolTrx {
        public static void main(String[] args) throws ParseException, SQLException {

                Servicio servicio = new RRHHServicio();

                // Obtener una fila por Id
                System.out.println("\r\n################ Historico porID ################\r\n");
                JobHistory historico = servicio.porIdHistorico(102);
                printObjeto(historico);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                String dateInStartDate = "13/01/01";
                String dateInEndDate = "24/07/06";

                // Update
                java.util.Date startDate = formatter.parse(dateInStartDate);
                java.util.Date endDate = formatter.parse(dateInEndDate);

                System.out.println("\r\n################ Historico Update ################\r\n");
                Jobs trabajo = new Jobs("IT_PROG", "Programmer", 4000, 10000, null);
                historico = new JobHistory(102, startDate, endDate, trabajo, 70);
                JobHistory update = servicio.guardarHistoricoConTrabajo(historico, trabajo);
                printObjeto(update);

                // Insert
                System.out.println("\r\n################ Historico Insert ################\r\n");
                historico = new JobHistory(108, startDate, endDate, trabajo, 60);
                JobHistory insert = servicio.guardarHistorico(historico);
                printObjeto(insert);

                // Eliminar
                System.out.println("\r\n################ Historico Eliminar ################\r\n");
                System.out.println("EMPLOYEE_ID: " + 108);
                servicio.eliminarHistorico(108);

                System.out.println("\r\n################ Historico Listado Completo ################\r\n");
                List<JobHistory> listaOrdenada = servicio.listarHistorico();
                listaOrdenada.sort(Comparator.comparing(JobHistory::getEmployeeId));
                listaOrdenada.forEach(EjemploDBConnectionPoolTrx::printObjeto);

                // Obtener una fila por Id
                System.out.println("\r\n################ Trabajo porID ################\r\n");
                trabajo = servicio.porIdTrabajo("HR_REP");
                printObjeto(trabajo);

                // Rompe la restricción de valor unico en la tabla, para hacer rollback
                /*
                String skuDuplicado = "abcd1234";

                // Insert
                System.out.println("\r\n################ Trabajo Insert ################\r\n");
                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, skuDuplicado);
                servicio.guardarTrabajo(trabajo);
                printObjeto(servicio.porIdTrabajo("IT_PROG_JR"));

                //Update
                System.out.println("\r\n################ Trabajo Update ################\r\n");
                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, skuDuplicado);
                servicio.guardarTrabajo(trabajo);
                printObjeto(servicio.porIdTrabajo("HR_REP"));
                */
                /*
                 * Este bloque no viola la restrucción del SKU
                 */

                // Insert
                System.out.println("\r\n################ Trabajo Insert ################\r\n");
                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, "abcd1234");
                servicio.guardarTrabajo(trabajo);
                printObjeto(servicio.porIdTrabajo("IT_PROG_JR"));

                // Update
                System.out.println("\r\n################ Trabajo Update ################\r\n");
                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, null);
                servicio.guardarTrabajo(trabajo);
                printObjeto(servicio.porIdTrabajo("HR_REP"));

                // Eliminar
                System.out.println("\r\n################ Trabajo Eliminar ################\r\n");
                System.out.println("JOB_ID: IT_PROG_JR");
                servicio.eliminarTrabajo("IT_PROG_JR");

                // Obtener una lista
                System.out.println("\r\n################ Trabajo Listado Completo ################\r\n");
                List<Jobs> listaTrabajo = servicio.listarTrabajo();
                listaTrabajo.forEach(EjemploDBConnectionPoolTrx::printObjeto);
                
                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, "abcd1234");
                historico = new JobHistory(102, startDate, endDate, null, 70);
                JobHistory nuevoHistorico = servicio.guardarHistoricoConTrabajo(historico, trabajo);
                Jobs nuevoTrabajo = servicio.porIdTrabajo(trabajo.getJobId());
                System.out.println("\r\n################ guardarHistoricoConTrabajo nuevoTrabajo ################\r\n");
                printObjeto(nuevoTrabajo);
                System.out.println("\r\n################ guardarHistoricoConTrabajo nuevoHistorico ################\r\n");
                printObjeto(nuevoHistorico);

        }

        public static void printObjeto(Object objeto) {
                System.out.println(objeto.toString());
        }

}