package es.neivi.seb.configuration;

import es.neivi.smb.annotation.AbstractSMBConfigurer;
import es.neivi.smb.handler.MessageHandler;

/**
 * Contract to provide the concreted expected instances to set a broadcast
 * channel.
 * 
 * Helpernotified by the environment for required properties to set a broadcast
 * channel through a mongodb tailable collection.
 */
public abstract class AbstractSEBConfigurer extends AbstractSMBConfigurer {

	/**
	 * How does SEB handle a Message=event being broadcasted? Once the event is
	 * obtained it let the Multicaster to notify to all listeners.
	 */
	public final MessageHandler messageHandler() {
		return new SEBMessageHandler();
	}
}
