package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Order;
import com.geekbrains.springbootproject.service.MailMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailMessageBuilderImpl implements MailMessageBuilder {
    private final TemplateEngine templateEngine;

    @Override
    public String buildOrderEmail(Order order) {
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("order-mail", context);
    }
}
