package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        reader.close();
        BufferedReader fr = new BufferedReader(new FileReader(fileName1));
        while (fr.ready()) {
            StringBuilder sb = new StringBuilder(fr.readLine());
            sb.reverse();
            System.out.println(sb);
        }
        fr.close();
    }
}
/*
D:\3.txt
 */