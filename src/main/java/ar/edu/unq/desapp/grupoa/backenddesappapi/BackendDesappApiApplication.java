package ar.edu.unq.desapp.grupoa.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class BackendDesappApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDesappApiApplication.class, args);
    }
}
