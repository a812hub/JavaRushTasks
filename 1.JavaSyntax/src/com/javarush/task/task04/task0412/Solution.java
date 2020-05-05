package com.javarush.task.task04.task0412;

/* 
Положительное и отрицательное число
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = Integer.parseInt(s);
        if (n >= 0) {
            n *= 2;

        }
        else {
            n++;
        }
        System.out.println(n);
    }
}