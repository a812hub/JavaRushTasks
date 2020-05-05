package com.javarush.task.task05.task0507;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Среднее арифметическое
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int sum = 0;
        int n;
        while ((n = Integer.parseInt(reader.readLine())) != -1) {
            sum += n;
            count++;
        }
        double avg = (double)sum / count;
        System.out.println(avg);
    }
}

