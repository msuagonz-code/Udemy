package org.sam.webapp.servlet.webapp.session.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public interface DataSourceProvider {
    BasicDataSource getDataSource();
}
