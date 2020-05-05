package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String s;
        double sum = 0;
        while (!(s = buffer.readLine()).equals("сумма")){
            sum += Double.parseDouble(s);
        }
        System.out.println(sum);
    }
}
