package com.javarush.task.task18.task1802;

import java.io.*;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String s = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            s = reader.readLine();
        } catch (IOException e) {}

        int min = -1;
        try (FileInputStream fis = new FileInputStream(s)) {
            int n;
            while (fis.available() > 0) {
                n = fis.read();
                if (n < min || min == -1) {
                    min = n;
                }
            }
        } catch (FileNotFoundException e) {}
        System.out.println(min);
    }
}
// D:\1.txt