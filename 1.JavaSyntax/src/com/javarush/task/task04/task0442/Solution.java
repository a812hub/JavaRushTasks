package com.javarush.task.task04.task0442;

/* 
Суммирование
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n;
        int sum = 0;
        do {
            n = in.nextInt();
            sum += n;
        } while (n != -1);
        System.out.println(sum);
        in.close();
    }
}
