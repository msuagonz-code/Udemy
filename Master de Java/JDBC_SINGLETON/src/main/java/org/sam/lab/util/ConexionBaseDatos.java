package org.sam.lab.util;

/*
* Patron Singleton
* https://refactoring.guru/es/design-patterns/singleton
* https://dzone.com/articles/java-concurrency-understanding-the-volatile-keyword
* https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static String user = "HR";
    private static String pass = "HR";
    private static volatile Connection connection;

    private ConexionBaseDatos(){
        //Constructor privado
    }

    //Double-Checked Locking
    public static Connection getConnectionInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {  // Verifica si la conexión sigue activa
            synchronized (ConexionBaseDatos.class) {  // Bloqueo para evitar hilos creando conexiones múltiples
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(url, user, pass);
                    System.out.println("\nConexión exitosa a la base de datos Oracle con DriverManager!\n");
                }
            }
        }
        return ConexionBaseDatos.connection;
    }

}
