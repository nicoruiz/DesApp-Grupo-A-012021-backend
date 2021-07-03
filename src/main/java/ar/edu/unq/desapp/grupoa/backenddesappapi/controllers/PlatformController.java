package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers;

import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.AuthRequestDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.JwtResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.PlatformDetailsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.RegistrationResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.services.PlatformsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@Api(tags = "Platforms")
public class PlatformController {

    @Autowired
    private PlatformsService platformsService;

    @PostMapping(value = "/authentication")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
            return ResponseEntity.ok().body(platformsService.login(authRequestDto));
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok().body(platformsService.register(authRequestDto));
    }

    @GetMapping(value = "/platforms/{name}")
    public ResponseEntity<PlatformDetailsDto> getPlatformDetails(@PathVariable String name) {
        return ResponseEntity.ok().body(platformsService.getPlatformDetails(name));
    }
}
