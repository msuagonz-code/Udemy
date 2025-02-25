package org.sam.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static String user = "HR";
    private static String pass = "HR";
    private static BasicDataSource pool;

    private ConnectionJDBC(){
        //Constructor privado
    }

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            synchronized (ConnectionJDBC.class) {
                if (pool == null) {
                    pool = new BasicDataSource();
                    pool.setUrl(url);
                    pool.setUsername(user);
                    pool.setPassword(pass);
                    pool.setInitialSize(3); // Conexiones minimas
                    pool.setMinIdle(3); // Conexiones minimas que no se están usando
                    pool.setMaxIdle(8); // Conexiones máximas que no se están usando
                    pool.setMaxTotal(8); // conexiones máximas totales
                }
            }
        }
        return pool;
    }

    public static Connection getConnecion() throws SQLException {
        return getInstance().getConnection();
    }

}
