package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CreateReportDto implements Serializable {
    @NotEmpty(message = "Comment may not be empty")
    private String comment;
}
