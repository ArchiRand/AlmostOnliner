package soso.production.model;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    @Column(name = "building_number")
    private String buildingNumber;
    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    @JoinColumn(name="user_id")
    private User addressOwner;

    public Address() {}
    public Address(String city, String street, String buildingNumber, String postalCode) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAddressOwner() {
        return addressOwner;
    }

    public void setAddressOwner(User addressOwner) {
        this.addressOwner = addressOwner;
    }

    public boolean isFullAddressSet() {
        return  (this.getId() != null) &&
                (this.getCity()!= null) &&
                (this.getBuildingNumber() != null) &&
                (this.getStreet() != null);
    }
}
