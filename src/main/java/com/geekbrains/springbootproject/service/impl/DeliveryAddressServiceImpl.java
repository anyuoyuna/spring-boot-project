package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.DeliveryAddress;
import com.geekbrains.springbootproject.entity.repository.DeliveryAddressRepository;
import com.geekbrains.springbootproject.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Override
    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepository.findAllByUserId(userId);
    }

    @Override
    public DeliveryAddress getUserAddressById(Long id) {
        return deliveryAddressRepository.findById(id).orElse(null);
    }
}
