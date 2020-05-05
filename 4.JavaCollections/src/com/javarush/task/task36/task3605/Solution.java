package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        TreeSet<Character> set = new TreeSet<>();
        try (FileReader fr = new FileReader(args[0])) {
            while (fr.ready()) {
                char ch = (char)fr.read();
                if (ch >= 'a' && ch <= 'z') {
                    set.add(ch);
                } else if (ch >= 'A' && ch <= 'Z') {
                    set.add((char)(ch + 32));
                }
            }
        }

        int i = 0;
        for (char s : set) {
            System.out.print(s);
            i++;
            if (i > 4) {
                break;
            }
        }

    }
}
