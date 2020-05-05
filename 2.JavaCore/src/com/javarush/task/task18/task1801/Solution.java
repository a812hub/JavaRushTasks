package com.javarush.task.task18.task1801;

import java.io.*;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String s = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            s = reader.readLine();
        } catch (IOException e) {
        }
        int max = 0;
        try (FileInputStream fis = new FileInputStream(s)) {
            int n;
            while (fis.available() > 0) {
                n = fis.read();
                if (n > max) {
                    max = n;
                }
            }
        } catch (FileNotFoundException e) {}
        System.out.println(max);
    }
}
// D:\1.txt