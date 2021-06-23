package ar.edu.unq.desapp.grupoa.backenddesappapi;

import ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class BackendDesappApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BackendDesappApiApplication.class, args);

        // Subscribe to RabbitMQ queue
        context.getBean(ConsumerService.class).subscribe();
    }
}
