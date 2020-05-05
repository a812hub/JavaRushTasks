package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            String[] line = reader.readLine().split(" ");
            for (String s : line) {
                if (s.matches(".{7,}")) {
                    sb.append(s).append(",");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        writer.write(sb.toString());
        reader.close();
        writer.close();
    }
}
