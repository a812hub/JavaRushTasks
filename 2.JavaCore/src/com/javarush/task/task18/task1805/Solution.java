package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();

        Set<Integer> set = new TreeSet<>();
        FileInputStream fis = new FileInputStream(s);
        while (fis.available() > 0) {
            set.add(fis.read());
        }
        fis.close();
        for (int i : set) {
            System.out.print(i + " ");
        }
    }
}
/*
D:\1.txt
 */