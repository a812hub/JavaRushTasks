package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Mike", 600);
        map.put("Mark", 500);
        map.put("John", 450);
        map.put("Jack", 600);
        map.put("Monika", 550);
        map.put("Justin", 700);
        map.put("Romeo", 150);
        map.put("Ralf", 4000);
        map.put("Angela", 1200);
        map.put("Elena", 1600);
        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> i = map.entrySet().iterator();
        while (i.hasNext()) {
            int salary = i.next().getValue();
            if (salary < 500) {
                i.remove();
            }
        }
    }

    public static void main(String[] args) {

    }
}