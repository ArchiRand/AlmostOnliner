package soso.production.service;

import soso.production.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryByName(String name);
    Category getCategoryById(Long id);
    Category save(Category category);
    List<Category> findAllCategories();
    Boolean deleteById(Long id);
}
