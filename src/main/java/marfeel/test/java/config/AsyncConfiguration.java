package marfeel.test.java.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import marfeel.test.java.service.MarfeelService;

@Configuration
@ComponentScan("marfeel.test.java.service")
@EnableAsync
public class AsyncConfiguration {

    @Bean
    public MarfeelService marfeelService () {
        return new MarfeelService();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("RunningTask-");
        executor.initialize();
        return executor;
    }
}