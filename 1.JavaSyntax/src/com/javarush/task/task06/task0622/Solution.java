package com.javarush.task.task06.task0622;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Числа по возрастанию
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[5];
        for (int i = 0; i < 5; i++) {
            a[i] = Integer.parseInt(reader.readLine());
        }
        sort(a);
        print(a);
    }

    public static void sort(int... a) {
        int tmp;
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a.length - i + 1; j++) {
                if (a[j - 1] > a[j]) {
                    tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    public static void print(int... a) {
        for (int i : a) {
            System.out.println(i);
        }
    }
}
