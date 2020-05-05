package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")) {

            String checkText = args[2];
            byte[] bText = new byte[checkText.length()];
            randomAccessFile.seek(Integer.parseInt(args[1]));
            randomAccessFile.read(bText, 0, checkText.length());
            randomAccessFile.seek(randomAccessFile.length());
            if (new String(bText).equals(checkText)) {
                randomAccessFile.write("true".getBytes());
            } else {
                randomAccessFile.write("false".getBytes());
            }

        } catch (IOException e) {
        }
    }
}

