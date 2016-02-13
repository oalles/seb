package es.omarall.seb.exceptions;

public class RootEventEntityMustBeAnApplicationEvent extends SEBException {

    public RootEventEntityMustBeAnApplicationEvent() {
        super();
    }

    public RootEventEntityMustBeAnApplicationEvent(String message,
            Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RootEventEntityMustBeAnApplicationEvent(String message,
            Throwable cause) {
        super(message, cause);
    }

    public RootEventEntityMustBeAnApplicationEvent(String message) {
        super(message);
    }

    public RootEventEntityMustBeAnApplicationEvent(Throwable cause) {
        super(cause);
    }
}
