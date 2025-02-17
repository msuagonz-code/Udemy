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

public class EjemploDBConnectionPoolTrx {
        public static void main(String[] args) throws ParseException, SQLException {

                try (Connection conn = ConexionBaseDatos.getConnecion()) {

                        if (conn.getAutoCommit()) {
                                // Se setea el auto commit a false para poder hacer rollback
                                conn.setAutoCommit(false);
                        }

                        try {
                                Repositorio<JobHistory, Integer> historicoRepositorio = new JobHistoryRepositoryImp(conn);
                                Repositorio <Jobs, String> trabajosRepositorio = new JobsRepositorioImpl(conn);

                                // Obtener una fila por Id
                                System.out.println("\r\n################ Historico porID ################\r\n");
                                JobHistory historico = historicoRepositorio.porId(102);
                                printObjeto(historico);

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                                String dateInStartDate = "13/01/01";
                                String dateInEndDate = "24/07/06";

                                //Update
                                java.util.Date startDate = formatter.parse(dateInStartDate);
                                java.util.Date endDate = formatter.parse(dateInEndDate);

                                System.out.println("\r\n################ Historico Update ################\r\n");
                                historico = new JobHistory(102, startDate, endDate, new Jobs("IT_PROG", null, null, null, null), 70);
                                JobHistory update = historicoRepositorio.guardar(historico);
                                printObjeto(update);

                                // Insert
                                System.out.println("\r\n################ Historico Insert ################\r\n");
                                historico = new JobHistory(108, startDate, endDate, new Jobs("IT_PROG", null, null, null, null), 60);
                                JobHistory insert = historicoRepositorio.guardar(historico);
                                printObjeto(insert);

                                // Eliminar
                                System.out.println("\r\n################ Historico Eliminar ################\r\n");
                                System.out.println("EMPLOYEE_ID: " + 108);
                                historicoRepositorio.eliminar(108);

                                System.out.println("\r\n################ Historico Listado Completo ################\r\n");
                                List<JobHistory> listaOrdenada = historicoRepositorio.listar();
                                listaOrdenada.sort(Comparator.comparing(JobHistory::getEmployeeId));
                                listaOrdenada.forEach(EjemploDBConnectionPoolTrx::printObjeto);

                                // Obtener una fila por Id
                                System.out.println("\r\n################ Trabajo porID ################\r\n");
                                Jobs trabajo = trabajosRepositorio.porId("HR_REP");
                                printObjeto(trabajo);

                                // Rompe la restricción de valor unico en la tabla, para hacer rollback
                                /*
                                String skuDuplicado = "abcd1234";

                                // Insert
                                System.out.println("\r\n################ Trabajo Insert ################\r\n");
                                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, skuDuplicado);
                                trabajosRepositorio.guardar(trabajo);
                                printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

                                //Update
                                System.out.println("\r\n################ Trabajo Update ################\r\n");
                                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, skuDuplicado);
                                trabajosRepositorio.guardar(trabajo);
                                printObjeto(trabajosRepositorio.porId("HR_REP"));
                                */

                                /*
                                 * Este bloque no viola la restrucción del SKU
                                 * */

                                // Insert
                                System.out.println("\r\n################ Trabajo Insert ################\r\n");
                                trabajo = new Jobs("IT_PROG_JR", "Junior Programmer", 4000, 4900, "abcd1234");
                                trabajosRepositorio.guardar(trabajo);
                                printObjeto(trabajosRepositorio.porId("IT_PROG_JR"));

                                //Update
                                System.out.println("\r\n################ Trabajo Update ################\r\n");
                                trabajo = new Jobs("HR_REP", "Recursos Humanos", 4200, 5000, null);
                                trabajosRepositorio.guardar(trabajo);
                                printObjeto(trabajosRepositorio.porId("HR_REP"));

                                // Eliminar
                                System.out.println("\r\n################ Historico Eliminar ################\r\n");
                                System.out.println("JOB_ID: IT_PROG_JR");
                                trabajosRepositorio.eliminar("IT_PROG_JR");

                                // Obtener una lista
                                System.out.println("\r\n################ Trabajo Listado Completo ################\r\n");
                                List <Jobs> listaTrabajo = trabajosRepositorio.listar();
                                listaTrabajo.forEach(EjemploDBConnectionPoolTrx::printObjeto);

                        conn.commit(); //Al finalizar las operacione se hace el commit
                        } catch (SQLException e) {
                                conn.rollback(); // Si ha ocurrido un error se hace rollback
                                e.printStackTrace();
                        }
                }
        }

        public static void printObjeto(Object objeto) {
                System.out.println(objeto.toString());
        }

}