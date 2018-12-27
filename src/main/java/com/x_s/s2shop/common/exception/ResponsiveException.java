package com.x_s.s2shop.common.exception;

public abstract class ResponsiveException extends SystemException {
    public ResponsiveException() {
        super();
    }

    public ResponsiveException(String message) {
        super(message);
    }

    public ResponsiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponsiveException(Throwable cause) {
        super(cause);
    }
}
