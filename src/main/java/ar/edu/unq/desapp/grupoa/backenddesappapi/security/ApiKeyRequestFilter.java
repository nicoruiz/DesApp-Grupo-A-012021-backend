package ar.edu.unq.desapp.grupoa.backenddesappapi.security;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiKeyRequestFilter extends GenericFilterBean {
    @Autowired
    private PlatformRepository platformRepository;

    private final List<String> excludedUrls = Arrays.asList("api/platforms", "api/authentication", "api/registration", "api/swagger",
            "api/webjars", "api/v2/api-docs", "api/configuration");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if(excludedUrls.stream().anyMatch(x -> path.contains(x))) {
            chain.doFilter(request, response);
            return;
        }

        String apiKey = req.getHeader("API-KEY");
        Platform platform = platformRepository.findByApiKey(apiKey);
        if(platform == null)
            throw new RuntimeException("Indicated API KEY is not valid.");

        chain.doFilter(request, response);
    }
}
