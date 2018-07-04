package soso.production.model.dto;

import soso.production.model.validators.MatchingPasswords;
import soso.production.model.validators.ValidEmail;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MatchingPasswords
public class UserDto {
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @Length(min=6)
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    public UserDto() {
    }

    public UserDto(@NotNull @NotEmpty String email, @NotNull @NotEmpty @Length(min = 6) String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
