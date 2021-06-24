package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import org.springframework.context.ConfigurableApplicationContext;

public interface Event {
    void handle();
}
