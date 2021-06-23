package ar.edu.unq.desapp.grupoa.backenddesappapi.services.rabbitmq;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.RabbitConfig;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitChannelUseException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.EmailSenderService;
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
    @Autowired
    private EmailSenderService emailSenderService;

    public void subscribe(String exchangeName, String email) {
        try {
            Connection connection = rabbitConfig.getConnectionInstance();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, "fanout");

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, "");

            System.out.println(" [x] Waiting for messages. " + email + " subscribed for title's news.");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String jsonReviewDto = new String(delivery.getBody(), StandardCharsets.UTF_8);
                ReviewDto review = new Gson().fromJson(jsonReviewDto, ReviewDto.class);
                System.out.println(" [x] Review updates " + review.getId() + " sending to: " + email);
                this.sendEmail(review, email);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        }
        catch (IOException ex) {
            throw new RabbitChannelUseException();
        }
    }

    private void sendEmail(ReviewDto review, String destinationEmail) {
        String subject = "New Reviews for Title: " + review.getTitlePrimaryTitle();
        String body = " - Resume: " + review.getResume() +
                    "\n - Body: " + review.getBody() +
                    "\n - Type: " + review.getReviewType() +
                    "\n - Rating: " + review.getRating() +
                    "\n - Platform: " + review.getPlatformUsername() +
                    "\n - User: " + review.getUsername();

        emailSenderService.sendEmail(destinationEmail, subject, body);
    }
}
