package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new HashMap<>();
        String[] ss;
        double maxAccount = 0;
        while (reader.ready()) {
            ss = reader.readLine().split(" ");
            map.merge(ss[0], Double.parseDouble(ss[1]), ((aDouble, aDouble2) -> aDouble + aDouble2));
            if (map.get(ss[0]) > maxAccount) {
                maxAccount = map.get(ss[0]);
            }
        }
        reader.close();
        for (Map.Entry<String,Double> entry : map.entrySet()) {
            if (entry.getValue() == maxAccount) {
                System.out.println(entry.getKey());
            }
        }
    }
}
