package ar.edu.unq.desapp.grupoa.backenddesappapi.config;

import ar.edu.unq.desapp.grupoa.backenddesappapi.messaging.DislikeReviewEvent;
import ar.edu.unq.desapp.grupoa.backenddesappapi.messaging.LikeReviewEvent;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq.ConsumerService;
import ar.edu.unq.desapp.grupoa.backenddesappapi.messaging.NewReviewEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class StartupConfig {
    public static ConfigurableApplicationContext ApplicationContext;

    public static void configure(ConfigurableApplicationContext context) {
        ApplicationContext = context;
        // Subscribe to RabbitMQ queues
        context.getBean(ConsumerService.class).subscribe(NewReviewEvent.class);
        context.getBean(ConsumerService.class).subscribe(LikeReviewEvent.class);
        context.getBean(ConsumerService.class).subscribe(DislikeReviewEvent.class);
    }
}
