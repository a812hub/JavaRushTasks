package com.javarush.task.task04.task0415;

/* 
Правило треугольника
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(reader.readLine());
        int n2 = Integer.parseInt(reader.readLine());
        int n3 = Integer.parseInt(reader.readLine());

        if (n1 + n2 > n3 && n2 + n3 > n1 && n3 + n1 > n2) System.out.println("Треугольник существует.");
        else System.out.println("Треугольник не существует.");

    }
}