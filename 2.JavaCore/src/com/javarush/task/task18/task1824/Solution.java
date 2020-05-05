package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String s = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                s = reader.readLine();
                try (FileInputStream fis = new FileInputStream(s)) {

                } catch (FileNotFoundException e) {
                    System.out.println(s);
                    break;
                }
            }
        } catch (IOException e) {}
    }
}
