package soso.production.service;

import soso.production.model.Category;
import soso.production.model.Product;
import soso.production.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("productService")
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Boolean deleteById(Long id) {
        Boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            exists = productRepository.existsById(id);
            if (!exists) {
                return true;
            }
        }
        return false;
    }
}
