package com.j13.bar.server.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOG {
    private static Logger LOG = LoggerFactory.getLogger(LOG.class);

    public static void info(String msg, Object... args) {
        LOG.info(msg, args);
    }

    public static void debug(String msg, Object... args) {
        LOG.debug(msg, args);
    }

    public static void warn(String msg, Object... args) {
        LOG.warn(msg, args);
    }

}
