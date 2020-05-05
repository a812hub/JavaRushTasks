package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int days = 365;
        if ((n % 400 == 0) || (n % 4 == 0 && n % 100 != 0)) days = 366;
        System.out.println("количество дней в году: " + days);
    }
}