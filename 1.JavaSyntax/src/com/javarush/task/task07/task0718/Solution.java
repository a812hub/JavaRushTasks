package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(in.readLine());
        }
        int length = -1;
        for (int i = 0; i < list.size(); i++) {
            if (length <= list.get(i).length()) {
                length = list.get(i).length();
            } else {
                System.out.println(i);
                break;
            }
        }
    }
}

