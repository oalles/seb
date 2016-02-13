package es.omarall.seb.exceptions;

/**
 * Indica la situacion en que un evento no podrá ser comunicado al EVENT BUS del
 * sistema. Normalmente por DataAccessException a MONGO según la implementacion
 * actual
 *
 * @author Omar
 *
 */
public class MulticastingException extends SEBException {

    public MulticastingException() {
    }

    public MulticastingException(String message) {
        super(message);
    }

    public MulticastingException(Throwable cause) {
        super(cause);
    }

    public MulticastingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MulticastingException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
