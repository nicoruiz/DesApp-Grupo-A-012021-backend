package ar.edu.unq.desapp.grupoa.backenddesappapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AuthRequestDto implements Serializable {
    @NotEmpty(message = "Username may not be empty")
    private String username;
    @NotEmpty(message = "Password may not be empty")
    private String password;

    public void toLowerCaseCredentials() {
        this.username = this.username.trim().toLowerCase();
        this.password = this.password.trim().toLowerCase();
    }

    public AuthRequestDto() {}
}
