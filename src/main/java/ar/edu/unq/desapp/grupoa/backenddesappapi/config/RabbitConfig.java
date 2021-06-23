package ar.edu.unq.desapp.grupoa.backenddesappapi.config;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitConnectionException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class RabbitConfig {
    @Value("${rabbitmq.uri}")
    private String uri;

    public Channel getChannel() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);
            Connection connection = factory.newConnection();

            return connection.createChannel();
        }
        catch (Exception ex) {
            throw new RabbitConnectionException();
        }
    }
}
