package com.geekbrains.springbootproject.service;

import com.geekbrains.springbootproject.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {
    List<DeliveryAddress> getUserAddresses(Long userId);
    DeliveryAddress getUserAddressById(Long id);
}
