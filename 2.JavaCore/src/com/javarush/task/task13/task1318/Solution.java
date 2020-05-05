package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        in.close();
        FileInputStream inFile = new FileInputStream(s);
        while (inFile.available() > 0) {
            int b = inFile.read();
            System.out.print((char)b);
        }
        inFile.close();
    }
}