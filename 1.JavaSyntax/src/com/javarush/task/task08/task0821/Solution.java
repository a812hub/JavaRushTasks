package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        Map<String, String> map = new HashMap<>();
        map.put("Mike", "Petrov");
        map.put("Mark", "Petrov");
        map.put("Mike", "Ivanov");
        map.put("Mike", "Sidorov");
        map.put("Donald", "Trump");
        map.put("Donald", "Duck");
        map.put("Angela", "Lebedeva");
        map.put("Josef", "Bro");
        map.put("Jesus", "Christ");
        map.put("Maria", "V");

        return map;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
