package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();
        map.put("Сталлоне", dateFormat.parse("MAY 1 2012"));
        map.put("Jack", dateFormat.parse("SEPTEMBER 1 2011"));
        map.put("Bred", dateFormat.parse("JUNE 1 2010"));
        map.put("Angelina", dateFormat.parse("AUGUST 31 2012"));
        map.put("Mark", dateFormat.parse("DECEMBER 1 2012"));
        map.put("Mike", dateFormat.parse("FEBRUARY 1 2012"));
        map.put("Lindsy", dateFormat.parse("MAY 14 2012"));
        map.put("Monika", dateFormat.parse("OCTOBER 1 2012"));
        map.put("Chandler", dateFormat.parse("OCTOBER 16 2012"));
        map.put("John", dateFormat.parse("AUGUST 14 2012"));
        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        Iterator<Map.Entry<String, Date>> i = map.entrySet().iterator();
        while (i.hasNext()) {
            Date d = i.next().getValue();
            if (d.getMonth() >= 5 && d.getMonth() <= 7) {
                i.remove();
            }
        }
    }

    public static void main(String[] args) {

    }
}
