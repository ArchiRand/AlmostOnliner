package soso.production.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 5)
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
