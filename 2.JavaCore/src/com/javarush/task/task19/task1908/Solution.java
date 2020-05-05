package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher;
        String currentLine;
        while (reader.ready()) {
            currentLine = reader.readLine();
            matcher = pattern.matcher(currentLine);
            while (matcher.find()) {
                writer.write(currentLine.substring(matcher.start(), matcher.end()));
                writer.write(" ");
            }
        }
        reader.close();
        writer.close();
    }
}
/*
D:\3.txt
D:\4.txt
 */