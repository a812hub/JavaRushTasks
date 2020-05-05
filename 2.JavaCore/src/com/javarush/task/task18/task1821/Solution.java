package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(args[0]));
        int currentByte;
        while (fis.available() > 0) {
            currentByte = fis.read();
            if (map.containsKey(currentByte)) {
                map.put(currentByte, map.get(currentByte) + 1);
            } else {
                map.put(currentByte, 1);
            }
        }
        fis.close();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println((char) (int) entry.getKey() + " " + entry.getValue());
        }
    }
}
