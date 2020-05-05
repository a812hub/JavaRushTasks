package com.javarush.task.task08.task0814;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        Set<Integer> set = new HashSet<>();
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -6, 13, 14, 15, 16, 17, 18, 19};
        set.addAll(Arrays.asList(a));
        return set;
    }

    public static Set<Integer> removeAllNumbersGreaterThan10(Set<Integer> set) {
        set.removeIf(a -> a > 10);
        return set;
    }

    public static void main(String[] args) {
        Set<Integer> set = createSet();
        set = removeAllNumbersGreaterThan10(set);
    }
}
