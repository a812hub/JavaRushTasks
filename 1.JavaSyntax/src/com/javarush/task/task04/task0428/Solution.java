package com.javarush.task.task04.task0428;

/* 
Положительное число
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int count = 0;
        if (a > 0) count++;
        if (b > 0) count++;
        if (c > 0) count++;

        System.out.println(count);

    }
}
