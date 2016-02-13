package es.omarall.seb.exceptions;

public class SEBException extends RuntimeException {

    public SEBException() {
    }

    public SEBException(String message) {
        super(message);
    }

    public SEBException(Throwable cause) {
        super(cause);
    }

    public SEBException(String message, Throwable cause) {
        super(message, cause);
    }

    public SEBException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
