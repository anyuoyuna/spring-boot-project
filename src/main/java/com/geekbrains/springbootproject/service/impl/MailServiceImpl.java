package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Order;
import com.geekbrains.springbootproject.service.MailService;
import com.geekbrains.springbootproject.service.impl.MailMessageBuilderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender sender;
    private final MailMessageBuilderImpl messageBuilder;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void sendMail(String email, String subject, String text) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            executorService.submit(() -> sender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendOrderMail(Order order) {
        sendMail(order.getUser().getEmail(), String.format("Заказ %d%n отправлен в обработку", order.getId()), messageBuilder.buildOrderEmail(order));
    }
}