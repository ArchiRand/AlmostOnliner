package soso.production.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 4879347754517805971L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "user")
    private Authority authoritie;

    @OneToMany(mappedBy = "cartOwner", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToOne(mappedBy = "addressOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Address address;

    @OneToOne(mappedBy = "phoneOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Phone phone;

    public User() {
        this.authoritie = new Authority();
        setAddress(new Address());
        setPhone(new Phone());
        setCarts(new ArrayList<>());
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, Authority authoritie) {
        this(email, password);
        this.authoritie = authoritie;
    }

    // <editor-fold desc="getters & setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Authority getAuthoritie() {
        return authoritie;
    }

    public void setAuthoritie(Authority authoritie) {
        this.authoritie = authoritie;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(authoritie, user.authoritie) &&
                Objects.equals(carts, user.carts) &&
                Objects.equals(address, user.address) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, password, authoritie, carts, address, phone);
    }

    // </editor-fold>

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
