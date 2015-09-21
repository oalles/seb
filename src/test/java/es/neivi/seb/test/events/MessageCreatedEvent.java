package es.neivi.seb.test.events;

import java.util.Date;

public class MessageCreatedEvent extends PersistentApplicationEvent {

	private final String message;

	public MessageCreatedEvent(String source, String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "MessageCreatedEvent {message: " + message + ", date: "
				+ new Date(getTimestamp()) + "}";
	}

}
