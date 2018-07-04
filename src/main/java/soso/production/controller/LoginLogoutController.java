package soso.production.controller;

import soso.production.model.User;
import soso.production.model.dto.LoginDto;
import soso.production.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("loginLogoutController")
public class LoginLogoutController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginDto());
        return "login_reg/login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("f") LoginDto loginDto, BindingResult result) {
        if (result.hasErrors()) {
            return "login_reg/login";
        }
        User user = userService.getByEmail(loginDto.getEmail());
        if (user == null) {
            result.addError(new FieldError("f", "email", "Пользователь с таким email не зарегистрирован =("));
            return "login_reg/login";
        } else {
            String password = user.getPassword();
            if (password.equals(loginDto.getPassword()) || passwordEncoder.matches(loginDto.getPassword(), password)) {
                return "shop";
            } else {
                result.addError(new FieldError("f", "email", "Упс! Вы ввели неверный пароль..."));
            }
            return "login_reg/login";
        }
    }

    @RequestMapping(value = "/user/loginFailed", method = RequestMethod.GET)
    public String loginFailed(Model model) {
        model.addAttribute("error", "true");
        return "login_reg/login";
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // Если текущей сессии нет, то и не создаем ее
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }
        return "login_reg/logout";
    }
}
