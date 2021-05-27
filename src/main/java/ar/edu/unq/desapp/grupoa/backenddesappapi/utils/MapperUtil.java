package ar.edu.unq.desapp.grupoa.backenddesappapi.utils;

import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.PlatformDetailsDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.RegistrationResponseDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.TitleDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos.reviews.ReviewDto;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Person;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Platform;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoa.backenddesappapi.model.Title;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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

        // TitleDto config
        this.modelMapper.typeMap(Title.class, TitleDto.class).addMappings(mapper -> {
            mapper.map(Title::getPersonNames, TitleDto::setPersons);
            mapper.map(Title::getReviewResumes, TitleDto::setReviewResumes);
            mapper.map(Title::getAverageRating, TitleDto::setAverageRating);
        });
    }
}
