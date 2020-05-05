package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s = in.readLine()).equals("exit")) {
            try {
                int n = Integer.parseInt(s);
                if (n > 0 && n < 128) {
                    print((short) n);
                } else {
                    print(n);
                }
            } catch (NumberFormatException e) {
                try {
                    Double d = Double.parseDouble(s);
                    print(d);
                } catch (NumberFormatException e1) {
                    print(s);
                }
            }
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
