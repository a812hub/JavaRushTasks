package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String s = in.readLine();
            s = s.substring(s.indexOf('?') + 1);
            String[] ss = s.split("&");
            StringBuilder sb = new StringBuilder();
            String obj = null;
            for (String s1 : ss) {
                if (s1.indexOf('=') >= 0) {
                    sb.append(s1.substring(0, s1.indexOf('=')));
                    sb.append(" ");
                } else {
                    sb.append(s1);
                    sb.append(" ");
                }
                if (s1.startsWith("obj=")) {
                    obj = s1.substring(s1.indexOf('=') + 1);
                }
            }
            System.out.println(sb);
            if (obj != null) {
                try {
                    alert(Double.parseDouble(obj));
                } catch (Exception e) {
                    alert(obj);
                }
            }
        } catch (IOException e) {
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}

