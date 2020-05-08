package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    // генерирует случайную строку.
    public static String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        BigInteger bigInteger = new BigInteger(121, new SecureRandom());
        return bigInteger.toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

}
