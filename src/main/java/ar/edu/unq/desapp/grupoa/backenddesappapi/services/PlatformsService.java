package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.AuthRequestDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.JwtResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.PlatformDetailsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.RegistrationResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.NonExistentPlatformException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.UsernameAlreadyExistsException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class PlatformsService {
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponseDto createAuthToken(AuthRequestDto authRequest) throws Exception {
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponseDto(userDetails.getUsername(), token);
    }

    public RegistrationResponseDto register(AuthRequestDto authReq) throws UsernameAlreadyExistsException {
        verifyUsername(authReq.getUsername());

        Platform newPlatform = new Platform();
        newPlatform.setUsername(authReq.getUsername());
        newPlatform.setPassword(authReq.getPassword());
        newPlatform.setApiKey(UUID.randomUUID().toString());
        platformRepository.save(newPlatform);

        return new RegistrationResponseDto(newPlatform.getId(), newPlatform.getUsername());
    }

    public PlatformDetailsDto getByUsername(String username) {
        Platform platform = platformRepository.findByUsername(username);
        if (platform == null)
            throw new NonExistentPlatformException(username);

        return new PlatformDetailsDto(platform.getUsername(), platform.getApiKey());
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private void verifyUsername(String username) throws UsernameAlreadyExistsException {
        Platform platform = platformRepository.findByUsername(username);
        if(platform != null) {
            throw new UsernameAlreadyExistsException(username);
        }
    }
}
