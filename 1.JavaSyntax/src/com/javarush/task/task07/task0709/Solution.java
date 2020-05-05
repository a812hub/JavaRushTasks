package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        int minLength = -1;
        for (int i = 0; i < 5; i++) {
            list.add(in.readLine());
            if (minLength > list.get(i).length() || minLength < 0) {
                minLength = list.get(i).length();
            }
        }
        for (String s : list) {
            if (s.length() == minLength) {
                System.out.println(s);
            }
        }
    }
}
