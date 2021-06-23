package ar.edu.unq.desapp.grupoa.backenddesappapi.config;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitChannelUseException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.RabbitConnectionException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitConfig {
    @Value("${rabbitmq.uri}")
    private String uri;
    private Connection connectionInstance = null;

    public Connection getConnectionInstance() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);

            if (connectionInstance == null) {
                connectionInstance = factory.newConnection();
            }
            return connectionInstance;
        }
        catch (Exception ex) {
            throw new RabbitConnectionException();
        }
    }

    public void closeChannel(Channel channel) {
        try {
            channel.close();
        }
        catch (TimeoutException | IOException ex) {
            throw new RabbitChannelUseException();
        }
    }
}
