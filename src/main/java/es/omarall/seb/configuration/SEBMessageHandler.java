package es.omarall.seb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import es.omarall.seb.event.SEBEventMulticaster;
import es.omarall.smb.handler.MessageHandler;

public class SEBMessageHandler implements MessageHandler {

	@Autowired(required = true)
	private SEBEventMulticaster multicaster;

	@Override
	public void handleMessage(Object event) {
		Assert.isTrue(event instanceof ApplicationEvent);
		multicaster.invokeListeners((ApplicationEvent) event);
	}

}
