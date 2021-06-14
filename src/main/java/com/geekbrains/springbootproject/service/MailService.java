package com.geekbrains.springbootproject.service;

import com.geekbrains.springbootproject.entity.Order;

public interface MailService {
    void sendMail(String email, String subject, String text);
    void sendOrderMail(Order order);
}
