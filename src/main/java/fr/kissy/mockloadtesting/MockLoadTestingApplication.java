package fr.kissy.mockloadtesting;

import fr.kissy.mockloadtesting.scheduling.JmxAwareThreadPoolTaskExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class MockLoadTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockLoadTestingApplication.class, args);
    }

    @Value("${datasource.executor.pool.size:10}")
    private Integer executorPoolSize;

    @Bean
    public AsyncTaskExecutor dataSourceExecutor() {
        ThreadPoolTaskExecutor executor = new JmxAwareThreadPoolTaskExecutor();
        executor.setThreadGroupName("datasource");
        executor.setThreadNamePrefix("datasource-");
        executor.setCorePoolSize(executorPoolSize);
        executor.setMaxPoolSize(executorPoolSize);
        executor.setQueueCapacity(0);
        executor.initialize();
        return executor;
    }
}
