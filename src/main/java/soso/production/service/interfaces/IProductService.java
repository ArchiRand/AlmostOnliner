package soso.production.service.interfaces;

import soso.production.model.Category;
import soso.production.model.Product;

import java.util.List;

public interface IProductService {
    Product save(Product product);
    Product getProductById(Long id);
    List<Product> findAllProducts();
    List<Product> findAllProductsByCategory(Category category);
    Boolean deleteById(Long id);
}
