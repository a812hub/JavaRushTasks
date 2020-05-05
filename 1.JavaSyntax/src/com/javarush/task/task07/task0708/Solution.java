package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings;

    public static void main(String[] args) throws Exception {
        strings = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int maxLength = -1;
        for (int i = 0; i < 5; i++) {
            strings.add(in.readLine());
            if (strings.get(i).length() > maxLength) {
                maxLength = strings.get(i).length();
            }
        }
        for (String s : strings) {
            if (s.length() == maxLength) {
                System.out.println(s);
            }
        }
    }
}
