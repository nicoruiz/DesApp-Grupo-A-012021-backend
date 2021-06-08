package ar.edu.unq.desapp.grupoa.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findByUsername(String username);
    Platform findByApiKey(String apiKey);
}
