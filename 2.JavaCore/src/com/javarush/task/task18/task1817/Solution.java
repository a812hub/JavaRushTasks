package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        int countSpace = 0;
        int countChar = 0;
        int currentByte;
        while (fis.available() > 0) {
            currentByte = fis.read();
            if (currentByte == (int)' ') countSpace++;
            countChar++;
        }
        fis.close();
        double result = countSpace * 100.0 / countChar;
        System.out.printf("%.2f", result);
    }
}
