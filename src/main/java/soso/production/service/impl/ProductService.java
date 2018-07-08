package soso.production.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soso.production.model.Category;
import soso.production.model.Product;
import soso.production.repository.ProductRepository;
import soso.production.service.interfaces.IProductService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public Product getProductById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return _sort(productRepository.findAll());
    }

    @Override
    public List<Product> findAllProductsByCategory(Category category) {
        return _sort(productRepository.findAllByCategory(category));
    }

    @Override
    public Boolean deleteById(Long id) {
        Boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            exists = productRepository.existsById(id);
            return !exists;
        }
        return false;
    }

    private List<Product> _sort(List<Product> products) {
        return products
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());
    }
}
