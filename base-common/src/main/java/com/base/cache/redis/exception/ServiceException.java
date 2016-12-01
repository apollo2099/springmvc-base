package com.base.cache.redis.exception;

public class ServiceException extends RuntimeException {

    private static final long	serialVersionUID	= 1L;

    public ServiceErrorCode		errorCode;

    public ServiceException() {
    }

    public ServiceException(ServiceErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ServiceException(String message, Throwable cause, ServiceErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, ServiceErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(Throwable cause, ServiceErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ServiceErrorCode getTec() {
        return errorCode;
    }
}