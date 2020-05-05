package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = new String[10];
        int[] n = new int[10];
        for (int i = 0; i < s.length; i++) {
            s[i] = reader.readLine();
            n[i] = s[i].length();
        }
        for (int i : n) {
            System.out.println(i);
        }
    }
}
