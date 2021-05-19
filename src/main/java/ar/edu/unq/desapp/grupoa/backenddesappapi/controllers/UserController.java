package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.UserDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.JwtResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.UsernameAlreadyExistsException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Api(tags = "Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/authentication")
    public ResponseEntity<JwtResponseDto> login(@RequestBody UserDto userDto) throws Exception {

            JwtResponseDto jwtToken = userService.createAuthToken(userDto);
            return ResponseEntity.ok(jwtToken);


    }

    @PostMapping(value = "/users/registration")
    public ResponseEntity<Long> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }
}
