package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> strings = new ArrayList<>();
        int indexMaxLength = -1;
        int indexMinLength = -1;
        int maxLength = -1;
        int minLength = -1;
        for (int i = 0; i < 10; i++) {
            strings.add(in.readLine());
            if (strings.get(i).length() > maxLength) {
                maxLength = strings.get(i).length();
                indexMaxLength = i;
            }
            if (strings.get(i).length() < minLength || minLength < 0) {
                minLength = strings.get(i).length();
                indexMinLength = i;
            }
        }
        if (indexMaxLength < indexMinLength) {
            System.out.println(strings.get(indexMaxLength));
        } else {
            System.out.println(strings.get(indexMinLength));
        }
    }
}
