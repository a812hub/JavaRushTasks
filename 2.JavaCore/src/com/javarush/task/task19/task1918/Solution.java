package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();
//        System.out.println(sb);
//        System.out.println("------------------");

        String tag = args[0];

        ArrayList<Integer> openIndex = new ArrayList<>();
        ArrayList<Integer> closeIndex = new ArrayList<>();
        int i = 0;
        while (sb.indexOf("<" + tag, i) >= 0 && i < sb.length()) {
            openIndex.add(sb.indexOf("<" + tag, i));
            i = sb.indexOf("<" + tag, i) + 1;
        }
        int j = 0;
        while (sb.indexOf("</" + tag + ">", j) >= 0 && j < sb.length()) {
            closeIndex.add(sb.indexOf("</" + tag + ">", j));
            j = sb.indexOf("</" + tag + ">", j) + 1;
        }


        ArrayList<String> list = new ArrayList<>();
        while (openIndex.size() > 0) {
            i = 0;
            j = 0;
            while (i < openIndex.size() - 1 && openIndex.get(i + 1) < closeIndex.get(j)) {
                i++;
                j++;
            }
            String s = sb.substring(openIndex.get(0), closeIndex.get(j) + tag.length() + 3);
            list.add(s);
            openIndex.remove(0);
            closeIndex.remove(j);
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
/*
D:\3.txt
 */
