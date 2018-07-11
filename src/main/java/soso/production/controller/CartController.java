package soso.production.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import soso.production.components.CartComponent;
import soso.production.model.Cart;
import soso.production.model.Product;
import soso.production.model.User;
import soso.production.model.dto.AdminCardDto;
import soso.production.service.interfaces.ICartService;
import soso.production.service.interfaces.IProductService;
import soso.production.service.interfaces.IUserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("request")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private CartComponent cartComponent;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;

    /**
     * Данный запрос обрабатывается когда пользователь кликает на кнопку "Добавить в корзину"
     * из shop.jsp
     *
     * @param id - товар для добавления в корзину
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cart/add/{id}", method = RequestMethod.POST, produces = "application/json")
    public Map<String, String> addToCart(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        int beforeAdd = cartComponent.getProductList().size();
        Product product = productService.getProductById(id);
        Hibernate.initialize(product);
        cartComponent.addProduct(product);
        if (cartComponent.getProductList().size() > beforeAdd) {
            response.put("completed", "true");
        } else {
            response.put("completed", "false");
        }
        return response;
    }

    /**
     * Данный запрос обрабатывается когда пользователь кликает на кнопку "Удалить"
     * из cartSummary.jsp
     *
     * @param id - товар для удаления из корзину
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cart/remove/{id}", method = RequestMethod.POST, produces = "application/json")
    public Map<String, String> removeFromCart(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        int beforeRemove = cartComponent.getProductList().size();
        Product product = productService.getProductById(id);
        cartComponent.removeProduct(product);
        if (cartComponent.getProductList().size() < beforeRemove) {
            response.put("completed", "true");
        } else {
            response.put("completed", "false");
        }
        return response;
    }

    /**
     * Данный запрос обрабатывается когда пользователь кликает на кнопку "Оформить покупку"
     * из shop.jsp
     *
     * @param model
     * @return view
     */
    @RequestMapping(value = "/cart/summary", method = RequestMethod.GET)
    public String goToBuying(Model model) {
        model.addAttribute("cart", cartComponent);
        return "cart/cartSummary";
    }

    /**
     * Итоговое оформление покупки.
     * Здесь перед сохранением проверяется заполненность полей адреса пользователя
     * и если все ок, то список товаров и итоговая стоимость из cartComponent добавляется в
     * объект Cart текущего пользователся, сам cartComponent очищается, а пользователь и корзина сохранятеся в бд
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cart/finalize", method = RequestMethod.POST, produces = "application/json")
    public Map<String, String> buyAndBeHappy(Principal principal) {
        Map<String, String> response = new HashMap<>();
        User user = userService.getByEmail(principal.getName());
        if (!cartComponent.getProductList().isEmpty()) {
            Cart newCart = new Cart(cartComponent.getProductList(), cartComponent.getTotalPrice(), user);
            cartService.save(newCart);
            user.getCarts().add(newCart);
            userService.save(user);
            cartComponent.clear();
            response.put("completed", "true");
        } else {
            response.put("completed", "false");
        }
        return response;
    }

    @RequestMapping(value = "/admin/cart/{id}", method = RequestMethod.GET)
    public String getInfo(@PathVariable("id") Long id, Model model) {
        Cart cart = cartService.findCartById(id);
        AdminCardDto adminCard = cartService.findAdminCartByCartId(id);
        model.addAttribute("products", cart.getProducts());
        model.addAttribute("address", cart.getCartOwner().getAddress());
        model.addAttribute("adminCard", adminCard);
        return "cart/cartInformation";
    }

}
