package com.gmail.ezlotnikova.springboot.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import com.gmail.ezlotnikova.repository.model.constant.UserRoleEnum;
import com.gmail.ezlotnikova.service.security.SecurityService;
import com.gmail.ezlotnikova.service.UserService;
import com.gmail.ezlotnikova.service.exception.UserExistsException;
import com.gmail.ezlotnikova.service.model.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    private final UserService userService;
    private final SecurityService securityService;

    public SignInController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login_page";
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("user", new UserDTO());
        attributes.put("roles", UserRoleEnum.values());
        return new ModelAndView("registration_page", attributes);
    }

    @PostMapping("/registration")
    public ModelAndView registerNewUser(
            @Valid @ModelAttribute(name = "user") UserDTO user,
            BindingResult errors) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("user", user);
        attributes.put("roles", UserRoleEnum.values());
        if (errors.hasErrors()) {
            return new ModelAndView("registration_page", attributes);
        } else {
            try {
                userService.add(user);
                securityService.autoLogin(
                        user.getUsername(),
                        user.getPassword());
                return new ModelAndView("redirect:/");
            } catch (UserExistsException e) {
                attributes.put("userAlreadyExistsError", e.getMessage());
                return new ModelAndView("registration_page", attributes);
            }
        }
    }

}