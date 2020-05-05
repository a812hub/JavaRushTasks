package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] strings;
        while (reader.ready()) {
            line = reader.readLine();
            strings = line.split(" ");
            int count = 0;
            for (String s : words) {
                count += Collections.frequency(Arrays.asList(strings), s);
            }
            if (count == 2) {
                System.out.println(line);
            }
        }
        reader.close();
    }
}
/*
D:\3.txt
 */