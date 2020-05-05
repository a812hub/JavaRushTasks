package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            String nextIndex = trimToSize(String.valueOf(findNextIndex(fileName)), 8);
            String productName = trimToSize(getFullProductName(args), 30);
            String price = trimToSize(args[args.length - 2], 8);
            String quantity = trimToSize(args[args.length - 1], 4);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
            writer.newLine();
            writer.write(nextIndex + productName + price + quantity);
            writer.close();
        }
    }

    public static String getFullProductName(String[] s) {
        StringBuilder sbProductName = new StringBuilder(s[1]);
        if (s.length > 4) {
            for (int i = 2; i < s.length - 2; i++) {
                sbProductName.append(" ").append(s[i]);
            }
        }
        return sbProductName.toString();
    }

    public static int findNextIndex(String fileName) throws IOException {
        String currentLine;
        int maxIndex = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        while (reader.ready()) {
            currentLine = reader.readLine();
            if (currentLine.startsWith("\uFEFF")) currentLine = currentLine.substring(1);
            int n = Integer.parseInt(currentLine.substring(0, 8).trim());
            if (n > maxIndex) {
                maxIndex = n;
            }
        }
        reader.close();
        return maxIndex + 1;
    }

    public static String trimToSize(String s, int size) {
        if (s.length() != size) {
            if (s.length() > size) {
                s = s.substring(0, size);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append(" ");
                }
                sb.replace(0, s.length(), s);
                s = sb.toString();
            }
        }
        return s;
    }
}
/*
D:\3.txt
 */