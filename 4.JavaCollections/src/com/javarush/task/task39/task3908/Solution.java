package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
                map.compute(s.charAt(i), (k, v) -> (v == null) ? 1 : v + 1);
        }
        long count = map.values().stream().filter(v -> v % 2 == 1).count();
        return count <= 1;
    }
}
