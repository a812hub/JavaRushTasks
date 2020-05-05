package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) return "";
        try (StringWriter writer = new StringWriter()) {
            int n = reader.read();
            while (n != -1) {
                writer.append((char)(n + key));
                n = reader.read();
            }
            return writer.toString();
        }
    }
}
