package soso.production.controller;

import soso.production.service.interfaces.IAddressService;
import soso.production.service.interfaces.ICartService;
import soso.production.service.interfaces.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller("userController")
public class UserController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value="/user", method= RequestMethod.GET)
    public String getUserProfile(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("username", email);
        model.addAttribute("addressObj", addressService.getAddressByUserEmail(email));
        model.addAttribute("phoneObj", phoneService.getPhoneByUserEmail(email));
        model.addAttribute("userCarts", cartService.findAllCartsByOwnerEmail(email));
        return "user/user";
    }
}
