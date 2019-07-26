package me.humin.lab.zq.exception;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ZQException extends RuntimeException {

    public ZQException(String message) {
        super(message);
    }

    public ZQException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZQException(Throwable cause) {
        super(cause);
    }
}
