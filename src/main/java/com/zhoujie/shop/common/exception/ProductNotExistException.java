package com.zhoujie.shop.common.exception;

public class ProductNotExistException extends Throwable {
    public ProductNotExistException() {
    }

    public ProductNotExistException(String message) {
        super(message);
    }

    public ProductNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotExistException(Throwable cause) {
        super(cause);
    }
}
