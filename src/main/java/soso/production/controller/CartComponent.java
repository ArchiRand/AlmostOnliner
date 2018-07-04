package soso.production.controller;

import soso.production.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class CartComponent {
    private List<Product> products;
    private BigDecimal fullPrice;

    public CartComponent() {
        this.products = new ArrayList<>();
        this.fullPrice = new BigDecimal(0);
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

    public void addProduct(Product product) {
        this.products.add(product);
        this.fullPrice = this.fullPrice.add(product.getPrice());
    }

    private Product getProductToRemove(Product product) {
        Product toRemove = null;
        Product iterProduct;
        for (int i = (this.products.size() - 1); i >= 0; i--) {
            iterProduct = this.products.get(i);
            if (product.getId().equals(iterProduct.getId())) {
                toRemove = iterProduct;
                break;
            }
        }
        return toRemove;
    }

    public void removeProduct(Product product) {
        Product productToRemove = getProductToRemove(product);
        this.products.remove(productToRemove);
        this.fullPrice = this.fullPrice.subtract(product.getPrice());
    }

    public boolean isEmpty() {
        return this.products.isEmpty();
    }

    public void clear() {
        this.products.clear();
        this.fullPrice = new BigDecimal(0);
    }
}
