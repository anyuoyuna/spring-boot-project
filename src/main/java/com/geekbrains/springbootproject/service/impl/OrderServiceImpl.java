package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Order;
import com.geekbrains.springbootproject.entity.OrderItem;
import com.geekbrains.springbootproject.entity.OrderStatus;
import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.entity.repository.OrderRepository;
import com.geekbrains.springbootproject.entity.repository.UserRepository;
import com.geekbrains.springbootproject.component.ShoppingCart;
import com.geekbrains.springbootproject.service.OrderService;
import com.geekbrains.springbootproject.service.impl.DeliveryAddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DeliveryAddressServiceImpl deliverAddressService;

    @Override
    @Transactional
    public Order makeOrder(ShoppingCart cart, Principal principal, String phoneNumber, Long deliveryAddressId) {
        User user = userRepository.findOneByUserName(principal.getName());
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setPrice(cart.getTotalCost());
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        order.setDeliveryAddress(deliverAddressService.getUserAddressById(deliveryAddressId));
        order.setPhoneNumber(phoneNumber);
        order.setDeliveryDate(LocalDateTime.now().plusDays(7));
        order.setDeliveryPrice(0.0);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order findOrder(Long id) {
        return orderRepository.findById(id).get();
    }
}
