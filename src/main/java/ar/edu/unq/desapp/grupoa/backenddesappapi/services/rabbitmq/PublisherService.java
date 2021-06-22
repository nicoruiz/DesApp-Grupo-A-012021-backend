package ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.RabbitConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitConnectionException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitQueueUseException;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PublisherService {
    @Autowired
    private RabbitConfig rabbitConfig;

    public void publish(String exchangeName, ReviewDto reviewDto) {
        Channel channel = rabbitConfig.getChannel();
        try {
            channel.exchangeDeclare(exchangeName, "fanout");

            String jsonReviewDto = new Gson().toJson(reviewDto, ReviewDto.class);
            channel.basicPublish(exchangeName, "", null, jsonReviewDto.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent review '" + reviewDto.getId() + "'");
        }
        catch (IOException ex) {
            throw new RabbitQueueUseException(exchangeName);
        }
    }
}
