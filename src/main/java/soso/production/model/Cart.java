package soso.production.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 7518629477937714222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "CART_PRODUCTS",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "full_price")
    private BigDecimal fullPrice;

    @ManyToOne
    @JoinColumn(name = "cart_owner")
    private User cartOwner;

    public Cart() {
        this.date = new Date();
    }

    public Cart(List<Product> products) {
        this();
        this.products = products;
    }

    public Cart(List<Product> products, BigDecimal fullPrice) {
        this();
        this.products = products;
        this.fullPrice = fullPrice;
    }

    public Cart(List<Product> products, BigDecimal fullPrice, User cartOwner) {
        this();
        this.products = products;
        this.fullPrice = fullPrice;
        this.cartOwner = cartOwner;
    }

    // <editor-fold desc="getters & setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public User getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(User cartOwner) {
        this.cartOwner = cartOwner;
    }
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(date, cart.date) &&
                Objects.equals(products, cart.products) &&
                Objects.equals(fullPrice, cart.fullPrice) &&
                Objects.equals(cartOwner, cart.cartOwner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, products, fullPrice, cartOwner);
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                '}';
    }
}
