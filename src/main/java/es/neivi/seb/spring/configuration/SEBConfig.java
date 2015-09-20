package es.neivi.seb.spring.configuration;

import java.util.Arrays;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import es.neivi.seb.event.SEBEventMulticaster;
import es.neivi.smb.annotation.EnableSMB;
import es.neivi.smb.annotation.SMBConfigurer;
import es.neivi.smb.handler.MessageHandler;

/**
 * Override this. MongoClient, databaseName and events collection name are
 * required.
 */
@Configuration
@EnableSMB(consumerId = "APP1")
@PropertySource("classpath:/META-INF/application.properties")
public class SEBConfig implements SMBConfigurer, EnvironmentAware {

	private Environment environment;

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public SEBEventMulticaster applicationEventMulticaster() {
		return new SEBEventMulticaster();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public TaskExecutor getTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(40);
		executor.setKeepAliveSeconds(0);
		executor.setThreadNamePrefix("SMB-");
		executor.initialize();
		return executor;
	}

	public MongoClient getMongoClient() throws Exception {
		MongoClientOptions options = MongoClientOptions.builder()
				.readPreference(ReadPreference.primary())
				.writeConcern(WriteConcern.JOURNALED).connectionsPerHost(10)
				.build();
		return new MongoClient(Arrays.asList(new ServerAddress("localhost")),
				options);
	}

	public String getDatabaseName() {
		return environment.getRequiredProperty("databasename");
	}

	@Bean
	public MessageHandler messageHandler() {
		return applicationEventMulticaster().getMessageHandler();
	}

	public long getCursorRegenerationDelay() {
		return 0;
	}
}
