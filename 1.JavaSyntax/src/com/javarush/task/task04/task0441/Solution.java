package com.javarush.task.task04.task0441;

/* 
Как-то средненько
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        if ((a >= b && b >= c) || (a <= b && b <= c)) {
            System.out.println(b);
        } else if ((b >= a && a >= c) || (b <= a && a <= c)) {
            System.out.println(a);
        } else {
            System.out.println(c);
        }
    }
}
