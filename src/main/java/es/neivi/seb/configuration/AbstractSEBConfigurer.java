package es.neivi.seb.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import es.neivi.smb.annotation.AbstractSMBConfigurer;
import es.neivi.smb.handler.MessageHandler;

/**
 * Override this. MongoClient, databaseName and events collection name are
 * required.
 */
public abstract class AbstractSEBConfigurer extends AbstractSMBConfigurer
		implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public MessageHandler messageHandler() {
		return new SEBMessageHandler();
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}
}