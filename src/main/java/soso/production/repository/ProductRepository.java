package soso.production.repository;

import soso.production.model.Category;
import soso.production.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
}
