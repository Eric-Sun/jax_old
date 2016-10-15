package com.j13.bar.server.poppy;

import com.j13.bar.server.core.config.PropertiesConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PoppyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertiesConfiguration.getInstance().addResource("/jax.properties");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
