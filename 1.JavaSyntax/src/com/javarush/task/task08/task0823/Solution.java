package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        StringBuilder result = new StringBuilder(string);
        for (int i = 0; i < result.length(); i++) {
            if (i == 0) {
                result.setCharAt(i, Character.toUpperCase(result.charAt(i)));
            } else if (result.charAt(i - 1) == ' '){
                result.setCharAt(i, Character.toUpperCase(result.charAt(i)));
            }
        }
        System.out.println(result);
    }
}
