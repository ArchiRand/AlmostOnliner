package soso.production.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="USERS_AUTHORITIES",
            joinColumns=@JoinColumn(name="user_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_fk", referencedColumnName = "id")
    )
    private List<Authority> authorities;

    @OneToMany(mappedBy="cartOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Cart> carts;

    @OneToOne(mappedBy="addressOwner", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Address address;

    @OneToOne(mappedBy="phoneOwner", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Phone phone;

    public User() {
        this.authorities = new ArrayList<>();
        // setAddress is used to establish OneToOne relationship between the User and the Address
        setAddress(new Address());
        // the same for Phone
        setPhone(new Phone());
        setCarts(new ArrayList<Cart>());
    }
    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, List<Authority> authorities) {
        this(email, password);
        this.authorities = authorities;
    }

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

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setAddressOwner(this);
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        phone.setPhoneOwner(this);
        this.phone = phone;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        cart.setCartOwner(this);
        this.carts.add(cart);
    }
}
