package com.chenzx.movie.config.exception;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/6 10:18
 */
public class BusException extends RuntimeException {

    public BusException() {
        super();
    }

    public BusException(String message) {
        super(message);
    }
}
