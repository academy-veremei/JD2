package by.academy.controller.listener;

import by.academy.dao.connection_pool.ConnectionPoolProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PoolInitializer implements ServletContextListener {
    ConnectionPoolProvider provider = ConnectionPoolProvider.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        provider.initPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        provider.disposePool();
    }
}
