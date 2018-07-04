package soso.production.controller;

import soso.production.model.Category;
import soso.production.model.Product;
import soso.production.service.ICategoryService;
import soso.production.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("shopController")
@Scope("request")
public class ShopController {

    @Autowired
    private CartComponent cart;

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String shop(Model model, @RequestParam(required=false, defaultValue="") String categoryName) {
        List<Product> productList;
        Integer cartSize = cart.getProducts().size();
        Category category = categoryService.getCategoryByName(categoryName);
        switch (categoryName) {
            case "":
            case "All":
                productList = productService.findAllProducts();
                break;
            default:
                productList = productService.findAllProductsByCategory(category);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("cart", cart);
        return "shop";
    }
}
