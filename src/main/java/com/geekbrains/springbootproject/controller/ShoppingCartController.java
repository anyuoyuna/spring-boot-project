package com.geekbrains.springbootproject.controller;

import com.geekbrains.springbootproject.service.impl.ShoppingCartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        model.addAttribute("cart", shoppingCartServiceImpl.getCurrentCart(httpSession));
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long productId, HttpServletRequest httpServletRequest) {
        shoppingCartServiceImpl.addToCart(httpServletRequest.getSession(), productId);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }
}
