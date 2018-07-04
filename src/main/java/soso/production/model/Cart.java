package soso.production.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "CART_PRODUCTS",
            joinColumns = @JoinColumn(name = "cart_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_fk", referencedColumnName = "id")
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
}
