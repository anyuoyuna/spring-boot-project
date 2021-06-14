package com.geekbrains.springbootproject.entity.repository;

import com.geekbrains.springbootproject.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
