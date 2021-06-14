package com.geekbrains.springbootproject.utils;

import org.springframework.boot.test.context.TestComponent;

import java.nio.charset.Charset;
import java.util.Random;

@TestComponent
public class RandomDataGenerator {

    public static long generateLong() {
        return new Random().nextLong();
    }

    public static String generateString() {
        return generateString(10);
    }

    public static String generateString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
