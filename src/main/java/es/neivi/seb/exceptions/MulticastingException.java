package es.neivi.seb.exceptions;

/**
 * Indica la situacion en que un evento no podrá ser comunicado al EVENT BUS del sistema.
 * Normalmente por DataAccessException a MONGO según la implementacion actual
 * @author Omar
 *
 */
public class MulticastingException extends SEBException {

	public MulticastingException() {
		// TODO Auto-generated constructor stub
	}

	public MulticastingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MulticastingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MulticastingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MulticastingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
