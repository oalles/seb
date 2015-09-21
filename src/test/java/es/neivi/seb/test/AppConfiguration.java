package es.neivi.seb.test;

import java.util.Arrays;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import es.neivi.seb.spring.configuration.AbstractSEBConfigurer;
import es.neivi.seb.spring.configuration.EnableSEB;
import es.neivi.smb.handler.MessageHandler;

@Configuration
@ComponentScan
@EnableSEB
@PropertySource("classpath:/META-INF/application.properties")
public class AppConfiguration extends AbstractSEBConfigurer {

	@Bean
	public MessageCreatedListener mcListener() {
		return new MessageCreatedListener();
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
}