package com.javarush.task.task39.task3904;

import java.util.Date;

/*
Лестница
*/
public class Solution {
    private static int n = 40;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0)
            return 0;
        if (n <= 1)
            return 1;
        if (n == 2)
            return 2;

        long[] cache = new long[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;
        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
        return cache[n];
    }

}

//The number of possible ascents for 40 steps is: 23837527729
