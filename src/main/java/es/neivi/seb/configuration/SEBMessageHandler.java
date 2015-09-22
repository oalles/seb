package es.neivi.seb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import es.neivi.seb.event.SEBEventMulticaster;
import es.neivi.smb.handler.MessageHandler;

public class SEBMessageHandler implements MessageHandler {

	@Autowired(required = true)
	private SEBEventMulticaster multicaster;

	@Override
	public void handleMessage(Object event) {
		Assert.isTrue(event instanceof ApplicationEvent);
		multicaster.invokeListeners((ApplicationEvent) event);
	}

}
