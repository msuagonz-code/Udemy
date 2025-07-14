package org.sam.webapp.servlet.webapp.session.utils;

/*
 * Patron Singleton
 * https://refactoring.guru/es/design-patterns/singleton
 * https://dzone.com/articles/java-concurrency-understanding-the-volatile-keyword
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
 * */

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDBDS {

    public Connection getConnection() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/OracleDB");
        return ds.getConnection();
    }

}
