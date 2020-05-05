package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        try {
            String s = args[0].toUpperCase();
            if (!s.matches("[0-9A-Z]+")) {
                System.out.println("incorrect");
            } else {
                char maxChar = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (maxChar < s.charAt(i)) {
                        maxChar = s.charAt(i);
                    }
                }
                if (maxChar >= '0' && maxChar <= '9') {
                    if (maxChar < '2') {
                        System.out.println("2");
                    } else {
                        System.out.println((int) maxChar - 47);
                    }
                } else {
                    System.out.println((int) maxChar - 54);
                }
            }
        } catch (Exception e) {
            //ntd
        }
    }
}