package ar.edu.unq.desapp.grupoa.backenddesappapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(bearerToken(), apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("ar.edu.unq.desapp.grupoa.backenddesappapi"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey bearerToken() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiKey apiKey() {
        return new ApiKey("API-KEY", "API-KEY", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Re-seña! API",
                "Re-seña! API Endpoints",
                "1.0",
                "Terms of service",
                new Contact("Re-seña!", "resena.com", "team@resena.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes),
                new SecurityReference("API-KEY", authorizationScopes)
        );
    }
}
