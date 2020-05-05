package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
        boolean num = false;
        boolean up = false;
        boolean low = false;
        for (int i = 0; i < 8; i++) {
            int n = (int) (Math.random() * 3);
            if (n == 0) {
                baos.write((int) (Math.random() * 10) + 48);
                num = true;
            } else if ( n == 1) {
                baos.write((int) (Math.random() * 26) + 65);
                up = true;
            } else if (n == 2) {
                baos.write((int) (Math.random() * 26) + 97);
                low = true;
            }
        }
        if (num && up && low) {
            return baos;
        }
        else {
            return getPassword();
        }
    }
}