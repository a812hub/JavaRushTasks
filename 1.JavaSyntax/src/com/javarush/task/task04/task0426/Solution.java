package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n == 0) System.out.println("ноль");
        else if (n > 0) {
            if (n % 2 == 0) System.out.println("положительное четное число");
            else System.out.println("положительное нечетное число");
        } else {
            if (n % 2 == 0) System.out.println("отрицательное четное число");
            else System.out.println("отрицательное нечетное число");
        }
        in.close();
    }
}
