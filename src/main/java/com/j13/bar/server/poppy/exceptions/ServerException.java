package com.j13.bar.server.poppy.exceptions;

public class ServerException extends RuntimeException {

    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(String msg, Throwable t) {
        super(msg, t);
    }
}
