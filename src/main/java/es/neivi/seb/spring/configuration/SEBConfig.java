package es.neivi.seb.spring.configuration;

import java.util.Arrays;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import es.neivi.seb.event.SEBEventMulticaster;
import es.neivi.smb.annotation.AbstractSMBConfigurer;
import es.neivi.smb.annotation.EnableSMB;
import es.neivi.smb.handler.MessageHandler;

/**
 * Override this. MongoClient, databaseName and events collection name are
 * required.
 */
@Configuration
@ComponentScan
@EnableSMB
@PropertySource("classpath:/META-INF/application.properties")
public class SEBConfig extends AbstractSMBConfigurer {

	@Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public SEBEventMulticaster applicationEventMulticaster() {
		return new SEBEventMulticaster();
	}

	@Override
	public Executor getExecutor() {

		// TaskExecutor executor = new SyncTaskExecutor();

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(40);
		executor.setKeepAliveSeconds(0);
		executor.setThreadNamePrefix("SMB-");
		executor.initialize();

		executor.setAwaitTerminationSeconds(60);
		return executor;
	}

	@Override
	public MongoClient getMongoClient() {
		MongoClientOptions options = MongoClientOptions.builder()
				.readPreference(ReadPreference.primary())
				.writeConcern(WriteConcern.JOURNALED).connectionsPerHost(10)
				.build();
		return new MongoClient(Arrays.asList(new ServerAddress("localhost")),
				options);
	}

	@Override
	public MessageHandler messageHandler() {
		return applicationEventMulticaster().getMessageHandler();
	}
}
