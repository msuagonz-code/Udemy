package org.sam.lab;

import org.sam.lab.modelo.JobHistory;
import org.sam.lab.modelo.Jobs;
import org.sam.lab.repositorio.Impl.JobHistoryRepositoryImp;
import org.sam.lab.repositorio.Repositorio;
import org.sam.lab.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class EjemploDBConnectionSingleton {
    public static void main(String[] args) {
        /*
        * https://www.oracle.com/es/database/technologies/faq-jdbc.html
        * https://github.com/brettwooldridge/HikariCP
        * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        * */

        // 1️⃣ `DriverManager` → Para conexiones simples, sin administración avanzada.
        // con un try-with-resources
        /*
        String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String user = "HR";
        String pass = "HR";

        try(Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM EMPLOYEES");
            ) {

            System.out.println("\nConexión exitosa a la base de datos Oracle con DriverManager!\n");
                while(resultado.next()){
                    System.out.print(resultado.getInt("EMPLOYEE_ID") +" ");
                    System.out.print("| " + resultado.getString("FIRST_NAME") +" ");
                    System.out.print("| " + resultado.getString("LAST_NAME") +" ");
                    System.out.println("| " + resultado.getDate("HIRE_DATE"));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

        // 2️⃣ `DataSource` → Para aplicaciones más escalables y con manejo de conexiones eficiente.
        /*
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@localhost:1521/XEPDB1");
            ds.setUser("HR");
            ds.setPassword("HR");

            try (Connection con = ds.getConnection()) {
                System.out.println("Conexión exitosa a la base de datos Oracle con DataSource!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        //3️⃣ Usando Singleton
        /*
        try(Connection con = ConexionBaseDatos.getConnectionInstance();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM EMPLOYEES");
        ) {
            while(resultado.next()){
                System.out.print(resultado.getInt("EMPLOYEE_ID") +" ");
                System.out.print("| " + resultado.getString("FIRST_NAME") +" ");
                System.out.print("| " + resultado.getString("LAST_NAME") +" ");
                System.out.println("| " + resultado.getDate("HIRE_DATE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

        //4️⃣ Usando repositorio
        try(Connection con = ConexionBaseDatos.getConnectionInstance()) {
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
            //System.out.println("\r\n################ porID ################\r\n");
            JobHistory historico = historicoRepositorio.porId(102);
            //printObjeto(historico);

            /*
            * 102	13/01/01	24/07/06	IT_PROG	60
            *
            * 108	13/01/01	24/07/06	IT_PROG	60
            * */

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
            listaOrdenada.forEach(EjemploDBConnectionSingleton::printObjeto);


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }


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