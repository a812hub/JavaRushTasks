package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            String s = in.readLine();
            if (s.equals("helicopter")) {
                result = new Helicopter();
            } else if (s.equals("plane")) {
                int n = Integer.parseInt(in.readLine());
                result = new Plane(n);
            }
        } catch (IOException e) {}
    }
}
