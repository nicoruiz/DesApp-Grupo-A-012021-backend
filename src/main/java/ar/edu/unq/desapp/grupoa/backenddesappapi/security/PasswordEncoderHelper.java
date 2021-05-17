package ar.edu.unq.desapp.grupoa.backenddesappapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderHelper {

    public String encryptPassword(String rawPassword) {
        return passwordEncoder().encode(rawPassword);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
