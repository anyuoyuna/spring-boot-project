package com.geekbrains.springbootproject.service;

import com.geekbrains.springbootproject.component.ShoppingCart;
import com.geekbrains.springbootproject.entity.Order;

import java.security.Principal;

public interface OrderService {
    Order makeOrder(ShoppingCart cart, Principal principal, String phoneNumber, Long deliveryAddressId);
    Order findOrder(Long id);
}
