package com.geekbrains.springbootproject.controller;

import com.geekbrains.springbootproject.entity.Order;
import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.service.impl.DeliveryAddressServiceImpl;
import com.geekbrains.springbootproject.service.impl.OrderServiceImpl;
import com.geekbrains.springbootproject.service.UserService;
import com.geekbrains.springbootproject.component.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderServiceImpl orderServiceImpl;
    private final DeliveryAddressServiceImpl deliverAddressService;
    private final ShoppingCart shoppingCart;

    @GetMapping("/fill")
    public String orderFill(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("deliveryAddresses", deliverAddressService.getUserAddresses(
                userService.findByUserName(principal).getId()));
        return "order-filler";
    }

    @PostMapping("/confirm")
    public String orderConfirm(
            Model model,
            Principal principal,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("deliveryAddress") Long deliveryAddressId) {

        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("order", orderServiceImpl.makeOrder(shoppingCart, principal, phoneNumber, deliveryAddressId));
        return "order-before-purchase";
    }

    @GetMapping("/result/{id}")
    public String orderConfirm(Model model, @PathVariable(name = "id") Long orderId, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal);
        Order confirmedOrder = orderServiceImpl.findOrder(orderId);
        if (!user.getId().equals(confirmedOrder.getUser().getId())) {
            return "redirect:/";
        }
        model.addAttribute("order", confirmedOrder);
        return "order-result";
    }
}
