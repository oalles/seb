package es.neivi.seb.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import es.neivi.seb.test.events.MessageCreatedEvent;

public class MessageCreatedListener implements
		ApplicationListener<MessageCreatedEvent> {

	private static Logger LOG = LoggerFactory
			.getLogger(MessageCreatedListener.class);

	public void onApplicationEvent(MessageCreatedEvent event) {
		LOG.warn("I ve processed an MCE: {}", event.toString());
	}
}
