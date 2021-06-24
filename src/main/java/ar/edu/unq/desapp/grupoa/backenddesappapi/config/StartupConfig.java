package ar.edu.unq.desapp.grupoa.backenddesappapi.config;

import ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq.ConsumerService;
import org.springframework.context.ConfigurableApplicationContext;

public class StartupConfig {
    public static void configure(ConfigurableApplicationContext context) {
        // Subscribe to RabbitMQ queue
        context.getBean(ConsumerService.class).subscribe();
    }
}
