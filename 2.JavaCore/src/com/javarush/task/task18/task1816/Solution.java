package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        int count = 0;
        int n;
        while (fis.available() > 0) {
             n = fis.read();
            if ((n >= 65 && n <= 90) || (n >= 97 && n <= 122)) {
                count++;
            }
        }
        fis.close();
        System.out.println(count);
    }
}
