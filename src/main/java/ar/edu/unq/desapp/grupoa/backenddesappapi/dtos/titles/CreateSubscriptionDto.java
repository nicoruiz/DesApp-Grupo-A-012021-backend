package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos.titles;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CreateSubscriptionDto implements Serializable {
    @Email
    @NotEmpty(message = "Email may not be empty")
    private String email;
}
