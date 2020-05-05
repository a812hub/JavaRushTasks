package com.javarush.task.task08.task0817;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Johnson", "Boris");
        map.put("Trump", "Donald");
        map.put("Duck", "Donald");
        map.put("Clinton", "Hilary");
        map.put("Pompeo", "Mike");
        map.put("May", "Teresa");
        map.put("Putin", "Vladimir");
        map.put("Merkel", "Angela");
        map.put("Macron", "Emmanuel");
        map.put("Erdogan", "Emmanuel");
        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        Map<String, String> copy = new HashMap<>(map);
        Iterator<Map.Entry<String, String>> i = copy.entrySet().iterator();
        while (i.hasNext()) {
            String s = i.next().getValue();
            if (Collections.frequency(copy.values(), s) > 1) {
                removeItemFromMapByValue(map, s);
            }
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {

    }
}
