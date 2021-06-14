package com.geekbrains.springbootproject.service;

import com.geekbrains.springbootproject.entity.Order;

public interface MailMessageBuilder {
    String buildOrderEmail(Order order);
}
