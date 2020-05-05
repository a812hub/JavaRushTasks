package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
        while (reader.ready()) {
            String currentString = reader.readLine();
            if (currentString.startsWith(args[0] + " ")) {
                System.out.println(currentString);
                break;
            }
        }
        reader.close();
    }
}
/*
D:\2.txt
 */