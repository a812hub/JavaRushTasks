package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        double t = in.nextDouble();
        int n = ((int) t) % 5;
        if (n >= 0 && n <= 2) System.out.println("зелёный");
        else if (n == 3) System.out.println("жёлтый");
        else System.out.println("красный");
    }
}