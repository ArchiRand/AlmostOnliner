package soso.production.controller;

import soso.production.model.Address;
import soso.production.model.Cart;
import soso.production.model.Product;
import soso.production.model.User;
import soso.production.model.dto.CartReportDto;
import soso.production.service.ICartService;
import soso.production.service.IProductService;
import soso.production.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller("cartController")
@Scope("request")
public class CartController {

    @Autowired
    private CartComponent cart;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value="/cart/add/{productId}", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody
    Map<String, String> addProductToCart(@PathVariable("productId") Long productId) {
        Map<String, String> requestResponse = new HashMap<>();
        Integer prevNumberOfProducts = cart.getProducts().size();
        Product product = productService.getProductById(productId);
        cart.addProduct(product);
        Integer currentNumberOfProducts = cart.getProducts().size();
        if ((prevNumberOfProducts + 1) == currentNumberOfProducts) {
            requestResponse.put("completed", "true");
        } else {
            requestResponse.put("completed", "false");
        }
        return requestResponse;
    }

    @RequestMapping(value="/cart/remove/{productId}", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody Map<String, String> removeProductFromCart(@PathVariable("productId") Long productId) {
        Map<String, String> requestResponse = new HashMap<>();
        Integer prevNumberOfProducts = cart.getProducts().size();

        Product product = productService.getProductById(productId);
        cart.removeProduct(product);
        Integer currentNumberOfProducts = cart.getProducts().size();

        if ((prevNumberOfProducts - 1) == currentNumberOfProducts) {
            requestResponse.put("completed", "true");
        } else {
            requestResponse.put("completed", "false");
        }
        return requestResponse;
    }

    @RequestMapping(value="/cart/summary", method=RequestMethod.GET)
    public String cartSummary(Model model) {
        model.addAttribute("cart", cart);
        return "cart/cartSummary";
    }

    @RequestMapping(value="/cart/finalize", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody Map<String, String> finalizeCart(Principal principal) {
        Map<String, String> requestResponse = new HashMap<>();

        String userEmail = principal.getName();
        User userObj = userService.getByEmail(userEmail);
        Address userAddress = userObj.getAddress();

        if (!cart.isEmpty() && userAddress.isFullAddressSet()) {
            Cart userCart = new Cart();
            userCart.setProducts(cart.getProducts());
            userCart.setFullPrice(cart.getFullPrice());

            cartService.save(userCart);

            userObj.addCart(userCart);
            userService.save(userObj);

            // clear the cart after finalization
            cart.clear();
            requestResponse.put("completed", "true");
        } else {
            requestResponse.put("completed", "false");
        }
        return requestResponse;
    }

    @RequestMapping(value="/admin/cart/{id}", method=RequestMethod.GET)
    public String getCartInformation(@PathVariable("id") Long id, Model model) {
        Cart cartObj = cartService.findCartById(id);
        CartReportDto cartReportDto = cartService.findCartReportByCartId(id);

        model.addAttribute("cartReport", cartReportDto);
        model.addAttribute("products", cartObj.getProducts());
        model.addAttribute("address", cartObj.getCartOwner().getAddress());
        return "cart/cartInformation";
    }
}
