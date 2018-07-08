package soso.production.service.impl;

import soso.production.model.Category;
import soso.production.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import soso.production.service.interfaces.ICategoryService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return _sort(categoryRepository.findAll());
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

    private List<Category> _sort(List<Category> categories) {
        return categories
                .stream()
                .sorted(Comparator.comparing(Category::getId))
                .collect(Collectors.toList());
    }
}
