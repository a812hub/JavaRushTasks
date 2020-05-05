package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.HashMap;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        String s;
        while (!(s = reader.readLine()).equals("")) {
            int id = Integer.parseInt(s);
            String name = reader.readLine();
            map.put(name, id);
        }
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }


    }
}
