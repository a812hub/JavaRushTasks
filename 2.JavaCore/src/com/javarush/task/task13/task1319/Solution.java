package com.javarush.task.task13.task1319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        String s = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            s = in.readLine();
            try (BufferedWriter out = new BufferedWriter(new FileWriter(s))) {
                while (true) {
                    s = in.readLine();
                    out.write(s);
                    out.newLine();
                    if (s.equals("exit")) break;
                }
            } catch (Exception e) {

            }
        } catch (IOException e) {

        }
    }
}