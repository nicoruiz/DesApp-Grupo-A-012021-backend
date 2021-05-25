package ar.edu.unq.desapp.grupoa.backenddesappapi.utils;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.PlatformDetailsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.RegistrationResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    private final ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.configure();
    }

    public ModelMapper getMapper() {
        return this.modelMapper;
    }

    private void configure() {
        // RegistrationResponseDto config
        this.modelMapper.typeMap(Platform.class, RegistrationResponseDto.class).addMappings(mapper -> {
            mapper.map(Platform::getUsername, RegistrationResponseDto::setPlatform);
        });

        // PlatformDetailsDto config
        this.modelMapper.typeMap(Platform.class, PlatformDetailsDto.class).addMappings(mapper -> {
            mapper.map(Platform::getUsername, PlatformDetailsDto::setPlatform);
        });
    }
}
