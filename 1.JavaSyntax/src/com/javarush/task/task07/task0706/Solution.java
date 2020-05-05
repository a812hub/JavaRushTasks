package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[15];
        int sumEven = 0;
        int sumOdd = 0;
        for (int i = 0; i < 15; i++) {
            a[i] = Integer.parseInt(reader.readLine());
            if (i % 2 == 0) {
                sumEven += a[i];
            } else {
                sumOdd += a[i];
            }
        }
        if (sumEven > sumOdd) {
            System.out.println("В домах с четными номерами проживает больше жителей.");
        }
        else if (sumEven < sumOdd) {
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        }
    }
}
