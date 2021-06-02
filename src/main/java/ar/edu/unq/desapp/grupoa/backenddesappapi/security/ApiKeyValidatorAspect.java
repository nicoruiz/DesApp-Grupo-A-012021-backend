package ar.edu.unq.desapp.grupoa.backenddesappapi.security;


import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.InvalidApiKeyException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(0)
public class ApiKeyValidatorAspect {
    static Logger logger = LoggerFactory.getLogger(ApiKeyValidatorAspect.class);
    private final List<String> excludedUrls = Arrays.asList("api/platforms", "api/authentication", "api/registration");
    @Autowired
    private PlatformRepository platformRepository;

    @Pointcut("execution(* ar.edu.unq.desapp.grupoa.backenddesappapi.controllers..*(..))")
    public void methodsStarterServicePointcut() {
    }

    @Before("methodsStarterServicePointcut()")
    public void beforeMethods() throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getRequestURI();
        if(excludedUrls.stream().noneMatch(x -> path.contains(x))) {
            logger.info("/////// BEFORE: VALIDATING API KEY /////");
            String apiKey = request.getHeader("API-KEY");
            validateApiKey(apiKey);
        }
    }

    private void validateApiKey(String apiKey) {
        Platform platform = platformRepository.findByApiKey(apiKey);
        if(platform == null)
            throw new InvalidApiKeyException();
        logger.info("/////// SUCCESS: API KEY VALIDATED "+ apiKey +" /////");
    }
}
