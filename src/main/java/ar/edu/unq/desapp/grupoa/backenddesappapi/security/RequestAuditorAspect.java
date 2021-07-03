package ar.edu.unq.desapp.grupoa.backenddesappapi.security;

import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.logging.RequestAuditEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(0)
public class RequestAuditorAspect {
    private final List<String> nonApiKeyUrls = Arrays.asList("api/platforms", "api/authentication", "api/registration");
    private final String jwtReqUrl = "api/platforms";
    @Autowired
    private ApiKeyValidator apiKeyValidator;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Around("execution(* ar.edu.unq.desapp.grupoa.backenddesappapi.controllers..*(..))")
    public Object auditRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String timeStamp = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now());
        String user = "Anonymous";
        String method = joinPoint.getSignature().getName();
        String params = Arrays.toString(joinPoint.getArgs());
        long executionTime;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getRequestURI();
        String apiKey = request.getHeader("API-KEY");
        String authorizationHeader = request.getHeader("Authorization");

        if (isApiKeyRequired(path)) {
            user = apiKeyValidator.validateApiKey(apiKey);
        }
        else if (isJwtRequired(path)) {
            String jwt = authorizationHeader.substring(7);
            user = jwtTokenUtil.getUsernameFromToken(jwt);
        }

        Object proceed = joinPoint.proceed();
        executionTime = System.currentTimeMillis() - start;

        this.logAuditEvent(timeStamp, user, method, params, executionTime);

        return proceed;
    }

    private boolean isApiKeyRequired(String path) {
        return nonApiKeyUrls.stream().noneMatch(x -> path.contains(x));
    }

    private boolean isJwtRequired(String path) {
        return path.contains(jwtReqUrl);
    }

    private void logAuditEvent(String timeStamp, String user, String method, String params, long executionTime) {
        RequestAuditEvent auditEvent = new RequestAuditEvent(timeStamp, user, method, params, executionTime);
        auditEvent.log();
    }
}
