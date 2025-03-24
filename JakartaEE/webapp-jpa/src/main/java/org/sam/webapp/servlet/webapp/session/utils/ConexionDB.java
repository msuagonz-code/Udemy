package org.sam.webapp.servlet.webapp.session.utils;

/*
 * Patron Singleton
 * https://refactoring.guru/es/design-patterns/singleton
 * https://dzone.com/articles/java-concurrency-understanding-the-volatile-keyword
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
 * */

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConexionDB {
    private final BasicDataSource dataSource;

    public ConexionDB(DataSourceProvider provider) {
        this.dataSource = provider.getDataSource();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
