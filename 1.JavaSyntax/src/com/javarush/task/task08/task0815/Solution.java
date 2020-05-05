package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Jackson", "Peter");
        map.put("Parker", "Peter");
        map.put("Wane", "Bruis");
        map.put("Jagger", "Mik");
        map.put("Lennon", "John");
        map.put("Travolta", "John");
        map.put("BonJovi", "John");
        map.put("Dolan", "Javier");
        map.put("Bardem", "Javier");
        map.put("Duck", "Donald");
        return map;
    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        int count = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(name)) {
                count++;
            }
        }
        return count;
    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        int count = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey().equals(lastName)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}


