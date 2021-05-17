package ar.edu.unq.desapp.grupoa.backenddesappapi.services;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.UserDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.JwtResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.UsernameAlreadyExistsException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.persistence.UserRepository;
import ar.edu.unq.desapp.grupoa.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupoa.backenddesappapi.security.PasswordEncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoderHelper passwordEncoderHelper;

    public JwtResponseDto createAuthToken(UserDto authRequest) throws Exception {
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponseDto(token);
    }

    public Long register(UserDto user) throws UsernameAlreadyExistsException {
        verifyUsername(user.getUsername());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoderHelper.encryptPassword(user.getPassword()));
        return userRepository.save(newUser).getId();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private void verifyUsername(String username) throws UsernameAlreadyExistsException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            throw new UsernameAlreadyExistsException(username);
        }
    }
}
