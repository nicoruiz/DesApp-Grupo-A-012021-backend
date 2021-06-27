package ar.edu.unq.desapp.grupoa.backenddesappapi.utils.logging;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class RequestAuditEvent implements LoggingEvent {
    private String timeStamp;
    private String user;
    private String method;
    private String params;
    private long executionTime;

    @Override
    public void log() {
        Logger logger = LogManager.getLogger(RequestAuditEvent.class);
        logger.info("[{}] User: {} - Method: {} - Parameters: {} - Execution Time: {}ms", this.timeStamp, this.user, this.method, this.params, this.executionTime);
    }

    public RequestAuditEvent(String timeStamp, String user, String method, String params, long executionTime) {
        this.timeStamp = timeStamp;
        this.user = user;
        this.method = method;
        this.params = params;
        this.executionTime = executionTime;
    }
}
