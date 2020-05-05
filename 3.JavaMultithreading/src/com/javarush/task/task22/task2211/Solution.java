package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        try (FileInputStream reader = new FileInputStream(args[0]);
             FileOutputStream writer = new FileOutputStream(args[1])) {
            while (reader.available() > 0) {
                byte[] buffer = new byte[128];
                reader.read(buffer);
                String s = new String(buffer, "Windows-1251");
                buffer = s.getBytes("UTF-8");
                writer.write(buffer);
            }
        } catch (FileNotFoundException e) {
        }
    }
}
