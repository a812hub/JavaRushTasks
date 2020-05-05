package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();

        Map<Integer, Integer> map = new HashMap<>();
        int n;
        FileInputStream fis = new FileInputStream(s);
        while (fis.available() > 0) {
            n = fis.read();
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        fis.close();
        int min = Collections.min(map.values());
        for (int i : map.keySet()) {
            if (map.get(i) == min) {
                System.out.print(i + " ");
            }
        }
    }
}
//D:\1.txt