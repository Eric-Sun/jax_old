package com.j13.bar.server.core;

import com.j13.bar.server.core.config.PropertiesConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JaxListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        PropertiesConfiguration.getInstance().addResource("/jax.properties");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
