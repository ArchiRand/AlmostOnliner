package soso.production.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class PhoneDto {
    @NotEmpty
    @Length(min=9, max=12)
    private String number;

    public PhoneDto() {}
    public PhoneDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
