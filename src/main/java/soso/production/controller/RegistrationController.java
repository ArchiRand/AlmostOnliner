package soso.production.controller;

import soso.production.model.Authority;
import soso.production.model.User;
import soso.production.model.dto.UserDto;
import soso.production.service.IAuthorityService;
import soso.production.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;

@Controller("registerController")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("registerForm", new UserDto());
        return "login_reg/register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String validateRegistrationForm(@Valid @ModelAttribute("registerForm") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "login_reg/register";
        }

        Authority authority = authorityService.getByAuthority("ROLE_USER");
        if (authority == null) {
            authority = new Authority("ROLE_USER");
        }
        User user = userService.getByEmail(userDto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setAuthorities(Arrays.asList(authority));
            userService.save(user);
            return "login_reg/successRegister";
        } else {
            result.addError(new FieldError("registerForm", "email", "User with that email already exists."));
            return "login_reg/register";
        }
    }
}
