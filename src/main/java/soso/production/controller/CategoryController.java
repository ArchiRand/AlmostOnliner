package soso.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import soso.production.model.Category;
import soso.production.service.interfaces.ICategoryService;
import soso.production.service.interfaces.IProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("categoryController")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/admin/categorypanel", method = RequestMethod.GET)
    public String getCategoryPanel(Model model) {
        model.addAttribute("categoryList", categoryService.findAllCategories());
        return "category/category";
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public String getCategoryForm(Model model) {
        model.addAttribute("categoryForm", new Category());
        return "category/addCategory";
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public String addNewCategory(@Valid @ModelAttribute("categoryForm") Category category, BindingResult result) {
        return _categoryService(category, result);
    }

    @RequestMapping(value = "/json/categories", method = RequestMethod.GET)
    public @ResponseBody
    List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @RequestMapping(value = "/admin/category/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    Map<String, String> deleteCategoryWithId(@PathVariable Long id) {
        Map<String, String> requestResponse = new HashMap<>();
        Boolean deleted = categoryService.deleteById(id);
        if (deleted) {
            requestResponse.put("deleted", "true");
        } else {
            requestResponse.put("deleted", "false");
        }
        return requestResponse;
    }

    @RequestMapping(value = "/admin/edit_category/{id}", method = RequestMethod.GET)
    public String editCategory(Model model, @PathVariable Long id) {
        model.addAttribute("categoryForm", categoryService.getCategoryById(id));
        return "category/addCategory";
    }

    @RequestMapping(value = "/admin/edit_category/{id}", method = RequestMethod.POST)
    public String updateCategory(@Valid @ModelAttribute Category category, BindingResult result) {
        return _categoryService(category, result);
    }

    private String _categoryService(Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/addCategory";
        }
        Category newCategory = categoryService.getCategoryByName(category.getName());
        if (newCategory == null) {
            if (category.getId() != null) {
                category.setProduct(productService.findAllProductsByCategory(category));
            }
            categoryService.save(category);
        } else {
            result.addError(new FieldError("categoryForm", "name", "Такая категория уже есть"));
        }
        return "redirect:/admin/categorypanel";
    }

}
