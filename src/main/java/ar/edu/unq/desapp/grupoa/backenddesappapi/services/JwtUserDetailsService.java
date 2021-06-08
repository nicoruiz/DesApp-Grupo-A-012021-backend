package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private PlatformRepository platformRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Platform platform = platformRepository.findByUsername(username);
        if (platform == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(platform.getUsername(), platform.getPassword(),
                new ArrayList<>());
    }
}
