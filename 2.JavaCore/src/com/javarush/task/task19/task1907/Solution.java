package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        int count = 0;
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            String[] ss = reader.readLine().split("[!.,;:\"\'? ]");
            for (String s : ss) {
                if (s.equals("world")) {
                    count++;
                }
            }
        }
        reader.close();
        System.out.println(count);
    }
}
/*
D:\3.txt
 */