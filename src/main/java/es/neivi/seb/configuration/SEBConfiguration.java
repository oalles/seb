package es.neivi.seb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import es.neivi.seb.event.SEBEventMulticaster;

@Configuration
public class SEBConfiguration {

	@Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public SEBEventMulticaster applicationEventMulticaster() {
		return new SEBEventMulticaster();
	}

}
