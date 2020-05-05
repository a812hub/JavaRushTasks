package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        for (int j = 0; j < array.length; j++) {
            int sIndex = -1;
            int nIndex = -1;
            for (int i = 0; i < array.length; i++) {
                if (isNumber(array[i])) {
                    if (nIndex < 0) {
                        nIndex = i;
                    } else {
                        if (Integer.parseInt(array[i]) > Integer.parseInt(array[nIndex])) {
                            String tmp = array[i];
                            array[i] = array[nIndex];
                            array[nIndex] = tmp;
                        }
                        nIndex = i;
                    }
                } else {
                    if (sIndex < 0) {
                        sIndex = i;
                    } else {
                        if (isGreaterThan(array[sIndex], array[i])) {
                            String tmp = array[i];
                            array[i] = array[sIndex];
                            array[sIndex] = tmp;
                        }
                        sIndex = i;
                    }
                }
            }
        }
    }


    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
