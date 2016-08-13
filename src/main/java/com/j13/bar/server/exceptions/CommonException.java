package com.j13.bar.server.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class CommonException extends RuntimeException {
    private static Logger LOG = LoggerFactory.getLogger(CommonException.class);
    private int errorCode = 0;

    public CommonException(int errorCode) {
        this.errorCode = errorCode;
    }


    public int getErrorCode() {
        return errorCode;
    }
}