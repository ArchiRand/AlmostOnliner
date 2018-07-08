package soso.production.model.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.context.annotation.SessionScope;
import soso.production.model.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@SessionScope
public class CategoryDto {

    private Long id;

    @NotEmpty
    @NotNull
    @Length(min=5, max = 50)
    private String name;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
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

}
