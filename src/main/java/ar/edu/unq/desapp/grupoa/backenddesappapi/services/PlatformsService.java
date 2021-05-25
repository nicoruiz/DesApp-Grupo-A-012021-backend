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
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.MapperUtil;
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
    @Autowired
    private MapperUtil mapperUtil;

    public JwtResponseDto login(AuthRequestDto authRequest) {
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponseDto(userDetails.getUsername(), token);
    }

    public RegistrationResponseDto register(AuthRequestDto authRequestDto) throws UsernameAlreadyExistsException {
        this.verifyCredentials(authRequestDto);

        Platform newPlatform = mapperUtil.getMapper().map(authRequestDto, Platform.class);
        newPlatform.setApiKey(UUID.randomUUID().toString());
        platformRepository.save(newPlatform);

        return mapperUtil.getMapper().map(newPlatform, RegistrationResponseDto.class);
    }

    public PlatformDetailsDto getPlatformDetails(String username) {
        Platform platform = platformRepository.findByUsername(username);
        if (platform == null)
            throw new NonExistentPlatformException(username);

        return mapperUtil.getMapper().map(platform, PlatformDetailsDto.class);
    }

    /*** Private methods ***/

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private void verifyCredentials(AuthRequestDto authRequestDto) {
        authRequestDto.verifyCredentials();
        this.verifyUsername(authRequestDto.getUsername());
    }

    private void verifyUsername(String username) throws UsernameAlreadyExistsException {
        Platform platform = platformRepository.findByUsername(username);
        if(platform != null) {
            throw new UsernameAlreadyExistsException(username);
        }
    }
}
