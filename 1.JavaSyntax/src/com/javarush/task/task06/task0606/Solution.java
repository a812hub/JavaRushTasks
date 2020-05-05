package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buffer.readLine());
        while (Math.abs(n) > 0) {
            if (n % 2 == 0) {
                even++;
            } else {
              odd++;
            }
            n /= 10;
        }
        System.out.printf("Even: %d Odd: %d\n", even, odd);
    }
}
