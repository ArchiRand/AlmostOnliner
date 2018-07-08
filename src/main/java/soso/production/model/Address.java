package soso.production.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="address")
public class Address implements Serializable {

    private static final long serialVersionUID = -2800423598858176466L;

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

    public Address() {

    }

    public Address(String city, String street, String buildingNumber, String postalCode) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    // <editor-fold desc="getters & setters">
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
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(buildingNumber, address.buildingNumber) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(addressOwner, address.addressOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, buildingNumber, postalCode, addressOwner);
    }

    // </editor-fold>

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                '}';
    }
}
