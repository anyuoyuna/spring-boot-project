package com.geekbrains.springbootproject.controller;

import com.geekbrains.springbootproject.entity.SystemUser;
import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser, BindingResult theBindingResult, Model theModel) {
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }
        if (userService.checkUserExisting(theSystemUser)) {
            theModel.addAttribute("systemUser", theSystemUser);
            theModel.addAttribute("registrationError", "User with current username already exists");
            return "registration-form";
        }
        return "registration-confirmation";
    }
}
