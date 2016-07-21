package com.j13.bar.server.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-4
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public class HDLogger {
    private static String KEY = "che";


    public static void info(HDLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger(KEY + "." + obj.getMod());
        log.info(obj.toString());
    }

    public static void error(HDLoggerEntity obj, Throwable t) {
        Logger log = LoggerFactory.getLogger(KEY + "." + obj.getMod());
        log.error(obj.toString(), t);
    }

    public static void error(HDLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger(KEY + "." + obj.getMod());
        log.error(obj.toString());
    }


    public static void debug(HDLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger(KEY + "." + obj.getMod());
        log.debug(obj.toString());
    }

}
