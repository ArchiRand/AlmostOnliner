package soso.production.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class AddressDto {

    @NotEmpty
    @Length(min=3, max=70)
    private String street;
    @NotEmpty
    @Length(min=2, max=100)
    private String city;

    @NotEmpty
    @Length(min=1, max=20)
    private String buildingNumber;

    @Length(min=6, max=6)
    private String postalCode;

    public AddressDto(){

    }

    public AddressDto(String street, String city, String buildingNumber, String postalCode) {
        this.street = street;
        this.city = city;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
