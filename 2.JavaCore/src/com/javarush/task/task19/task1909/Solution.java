package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader (fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        String currentLine;
        while (reader.ready()) {
            currentLine = reader.readLine();
            writer.write(currentLine.replaceAll("\\.", "!"));
        }
        reader.close();
        writer.close();
    }
}
/*
D:\3.txt
D:\4.txt
 */