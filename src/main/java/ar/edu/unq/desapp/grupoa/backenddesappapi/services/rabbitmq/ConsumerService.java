package ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.RabbitConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitChannelUseException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.messaging.Event;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class ConsumerService {
    @Autowired
    private RabbitConfig rabbitConfig;

    public <T extends Event> void subscribe(Class<T> eventClass)  {
        try {
            Connection connection = rabbitConfig.getConnectionInstance();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(rabbitConfig.EXCHANGE_NAME, "fanout");
            String queueName = channel.queueDeclare(eventClass.getSimpleName(), true, false, false, null).getQueue();
            channel.queueBind(queueName, rabbitConfig.EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String jsonEvent = new String(delivery.getBody(), StandardCharsets.UTF_8);
                T event = new Gson().fromJson(jsonEvent, eventClass);
                event.handle();
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }
        catch (IOException ex) {
            throw new RabbitChannelUseException();
        }
    }
}
