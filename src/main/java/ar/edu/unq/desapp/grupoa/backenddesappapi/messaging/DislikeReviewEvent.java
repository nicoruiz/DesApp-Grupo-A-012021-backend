package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class DislikeReviewEvent implements Serializable, Event {
    @Override
    public void handle() {
        Logger logger = LogManager.getLogger(DislikeReviewEvent.class);
        logger.info("[RabbitMQ] Dislike Review Event handled successfully.");
    }
}
