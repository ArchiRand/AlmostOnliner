package soso.production.service;

import soso.production.model.Category;
import soso.production.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("categoryService")
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        Boolean exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
            exists = categoryRepository.existsById(id);
            if (!exists) {
                return true;
            }
        }
        return false;
    }
}
