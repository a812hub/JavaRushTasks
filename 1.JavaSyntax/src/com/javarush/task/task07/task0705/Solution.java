package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] a1 = new int[20];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = Integer.parseInt(reader.readLine());
        }

        int[] a2 = new int[10];
        int[] a3 = new int[10];

        for (int i = 0; i < a2.length; i++) {
            a2[i] = a1[i];
        }
        for (int i = 0; i < a3.length; i++) {
            a3[i] = a1[i + a2.length];
        }
        for (int i : a3) {
            System.out.println(i);
        }
    }
}
