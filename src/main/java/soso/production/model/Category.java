package soso.production.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category implements Serializable, Persistable {

    private static final long serialVersionUID = 354369044324982088L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 30)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category", orphanRemoval = true)
    private List<Product> products;

    // <editor-fold desc="getters & setters">
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> products) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        if (products != null && !products.isEmpty()) {
            this.products.clear();
            this.products.addAll(products);
        }
    }
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(products, category.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                '}';
    }
}
