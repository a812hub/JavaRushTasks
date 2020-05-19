package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 400; i++) {
            if (isPowerOfThree(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPowerOfThree(int n) {
        double log3 = Math.log(n) / Math.log(3);
        double eps = 0.000001;
        return (1 - log3 % 1) < eps || log3 % 1 < eps;
    }
}
