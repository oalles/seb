package es.neivi.seb.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import es.neivi.seb.event.SEBEventMulticaster;
import es.neivi.smb.annotation.SMBConfiguration;
import es.neivi.smb.handler.MessageHandler;

@Configuration("smbConfig")
public class SEBConfiguration extends SMBConfiguration {

	@Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public SEBEventMulticaster applicationEventMulticaster() {
		return new SEBEventMulticaster();
	}

}