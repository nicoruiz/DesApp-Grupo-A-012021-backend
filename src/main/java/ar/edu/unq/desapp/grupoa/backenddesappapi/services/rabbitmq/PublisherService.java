package ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.RabbitConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitChannelUseException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.messaging.Event;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PublisherService {
    @Autowired
    private RabbitConfig rabbitConfig;

    public <T extends Event> void publish(T event) {
        try {
            Connection connection = rabbitConfig.getConnectionInstance();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(rabbitConfig.EXCHANGE_NAME, "fanout");

            String jsonEvent = new Gson().toJson(event, event.getClass());
            channel.basicPublish(rabbitConfig.EXCHANGE_NAME, "", null, jsonEvent.getBytes(StandardCharsets.UTF_8));
            rabbitConfig.closeChannel(channel);
        }
        catch (IOException ex) {
            throw new RabbitChannelUseException();
        }
    }
}
