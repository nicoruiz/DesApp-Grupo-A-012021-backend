package ar.edu.unq.desapp.grupoa.backenddesappapi.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class LikeReviewEvent implements Serializable, Event {
    @Override
    public void handle() {
        Logger logger = LogManager.getLogger(LikeReviewEvent.class);
        logger.info("[RabbitMQ] Like Review Event handled successfully.");
    }
}
