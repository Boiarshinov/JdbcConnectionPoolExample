package dev.boiarshinov.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import dev.boiarshinov.dao.NonogramDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.beans.PropertyVetoException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class WebConfig implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("App started at {}", new Date());
        registerDBDrivers();
        ComboPooledDataSource connectionPool = getConnectionPool();
        sce.getServletContext().setAttribute("nonogramDAO", new NonogramDAO(connectionPool));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("App destroyed at {}", new Date());
    }

    private ComboPooledDataSource getConnectionPool() {
        logger.debug("Initializing connection pool");
        ComboPooledDataSource connectionPool = new ComboPooledDataSource();

        try {
            connectionPool.setDriverClass   ("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        connectionPool.setJdbcUrl       ("jdbc:mysql://localhost:3306/test");
        connectionPool.setUser          ("boiarshinov");
        connectionPool.setPassword      ("boiarshinov");

        Properties properties = new Properties();
        properties.setProperty          ("serverTimezone",  "Europe/Moscow");
        properties.setProperty          ("user",            "boiarshinov");
        properties.setProperty          ("password",        "boiarshinov");

        connectionPool.setProperties(properties);

        connectionPool.setMaxStatements             (180);
        connectionPool.setMaxStatementsPerConnection(180);
        connectionPool.setMinPoolSize               ( 3);
        connectionPool.setAcquireIncrement          ( 10);
        connectionPool.setMaxPoolSize               ( 10);
        connectionPool.setMaxIdleTime               ( 30);

        return connectionPool;
    }

    private void registerDBDrivers(){
        logger.debug("Registering DB driver");
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
