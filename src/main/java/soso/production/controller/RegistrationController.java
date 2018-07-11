package soso.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import soso.production.model.Authority;
import soso.production.model.User;
import soso.production.model.dto.UserDto;
import soso.production.service.interfaces.IAuthorityService;
import soso.production.service.interfaces.IUserService;

import javax.validation.Valid;

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
        if (!_validatePassword(userDto)) {
            result.addError(new FieldError("registerForm", "password2", "Неправильно подтвердили пароль!"));
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
            user.setAuthoritie(authority);
            authority.setUser(user);
            userService.save(user);
            return "login_reg/successRegister";
        } else {
            result.addError(new FieldError("registerForm", "email", "Пользователь с таким email уже существует"));
            return "login_reg/register";
        }
    }

    private boolean _validatePassword(UserDto userDto) {
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
