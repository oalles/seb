package es.omarall.seb.test;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import es.omarall.seb.configuration.AbstractSEBConfigurer;
import es.omarall.seb.configuration.EnableSEB;

@Configuration
@ComponentScan
@PropertySource("classpath:/META-INF/application.properties")
@EnableSEB("es.omarall")
public class AppConfiguration extends AbstractSEBConfigurer {

    @Bean
    public MessageCreatedListener mcListener() {
        return new MessageCreatedListener();
    }

    @Override
    public TaskExecutor getExecutor() {

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
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}