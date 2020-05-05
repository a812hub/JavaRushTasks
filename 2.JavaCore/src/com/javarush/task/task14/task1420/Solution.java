package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int a = Integer.parseInt(in.readLine());
            int b = Integer.parseInt(in.readLine());
            if (a <= 0 || b <= 0) {
                throw new Exception();
            }
            int c = nod(a, b);
            System.out.println(c);
    }

    public static int nod(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        } else if (a % b == 0) {
            return b;
        } else {
            return nod(b, a % b);
        }
    }
}
