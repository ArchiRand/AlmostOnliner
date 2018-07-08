package soso.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import soso.production.model.Category;
import soso.production.model.Product;
import soso.production.model.dto.ProductDto;
import soso.production.service.interfaces.ICategoryService;
import soso.production.service.interfaces.IProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller("productController")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/admin/productpanel", method = RequestMethod.GET)
    public String getProductPanel(Model model) {
        model.addAttribute("productList", productService.findAllProducts());
        return "product/product";
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String getProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product/addProduct";
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.POST)
    public String addNewProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result) {
        return _productService(productDto, result);
    }

    @RequestMapping(value = "/admin/product/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    Map<String, String> deleteProductWithId(@PathVariable Long id) {
        Map<String, String> requestResponse = new HashMap<>();
        Boolean deleted = productService.deleteById(id);
        if (deleted) {
            requestResponse.put("deleted", "true");
        } else {
            requestResponse.put("deleted", "false");
        }
        return requestResponse;
    }

    @RequestMapping(value = "/admin/edit_product/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", _fromEntityToDto(product));
        return "product/addProduct";
    }

    @RequestMapping(value = "/admin/edit_product/{id}", method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result) {
        return _productService(productDto, result);
    }

    private String _productService(ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return "product/addProduct";
        }
        Category category = categoryService.getCategoryById(productDto.getCategoryId());
        if (category == null) {
            result.addError(new FieldError("product", "categoryId", "Такая категоря не существует!"));
            return "product/addProduct";
        }
        Product product = _fromDtoToEntity(productDto, category);
        productService.save(product);
        return "redirect:/admin/productpanel";
    }

    private Product _fromDtoToEntity(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        return product;
    }

    private ProductDto _fromEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

}
