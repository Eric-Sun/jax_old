package com.j13.bar.server.core.exception;

public class CommonRequestException extends RuntimeException {

    public CommonRequestException(String msg, Throwable t) {
        super(msg, t);
    }
}
