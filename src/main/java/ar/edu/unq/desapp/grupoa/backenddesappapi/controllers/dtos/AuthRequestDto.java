package ar.edu.unq.desapp.grupoa.backenddesappapi.controllers.dtos;

import ar.edu.unq.desapp.grupoa.backenddesappapi.model.exceptions.InvalidCredentialsException;
import ar.edu.unq.desapp.grupoa.backenddesappapi.utils.StringHelper;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestDto implements Serializable {
    private String username;
    private String password;

    public void verifyCredentials() {
        if(StringHelper.isNullOrEmpty(this.username) || StringHelper.isNullOrEmpty(this.password))
            throw new InvalidCredentialsException();

        this.toLowerCaseCredentials();
    }

    private void toLowerCaseCredentials() {
        this.username = this.username.trim().toLowerCase();
        this.password = this.password.trim().toLowerCase();
    }

    public AuthRequestDto() {}
}
