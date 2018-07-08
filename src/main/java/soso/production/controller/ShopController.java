package soso.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import soso.production.components.CartComponent;
import soso.production.model.Category;
import soso.production.model.Product;
import soso.production.service.interfaces.ICategoryService;
import soso.production.service.interfaces.IProductService;

import java.util.List;

@Controller
@Scope("request")
public class ShopController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private CartComponent cartComponent;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadMainPage(Model model, @RequestParam(required=false, defaultValue="") String category) {
        List<Product> productList;
        Integer cartSize = cartComponent.getProductList().size();
        switch (category) {
            case "":
            case "All":
                productList = productService.findAllProducts();
                break;
            default:
                Category category1 = categoryService.getCategoryByName(category);
                productList = productService.findAllProductsByCategory(category1);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("cart", cartComponent);
        return "shop";
    }

}
