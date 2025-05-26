package org.sam.webapp.servlet.webapp.session.utils;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class BasicDataSourceProvider implements DataSourceProvider{
    private static volatile BasicDataSource dataSource;

    public BasicDataSourceProvider() {
        if (dataSource == null) {
            synchronized (BasicDataSourceProvider.class) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName(ConfigLoader.get("db.driver"));
                    dataSource.setUrl(ConfigLoader.get("db.url"));
                    dataSource.setUsername(ConfigLoader.get("db.user"));
                    dataSource.setPassword(ConfigLoader.get("db.pass"));
                    dataSource.setInitialSize( Integer.valueOf(ConfigLoader.get("db.pool.setInitialSize")) );
                    dataSource.setMinIdle(Integer.valueOf(ConfigLoader.get("db.pool.setMinIdle")));
                    dataSource.setMaxIdle(Integer.valueOf(ConfigLoader.get("db.pool.setMaxIdle")));
                    dataSource.setMaxTotal(Integer.valueOf(ConfigLoader.get("db.pool.setMaxTotal")));
                }
            }
        }
    }

    @Override
    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
