package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

           int tmp;
        if (a < c) {
            tmp = c;
            c = a;
            a = tmp;
        }
        if (b < c) {
            tmp = c;
            c = b;
            b = tmp;
        }
        if (a < b) {
            tmp = b;
            b = a;
            a = tmp;
        }
        System.out.println(a + " " + b + " " + c);
    }
}
