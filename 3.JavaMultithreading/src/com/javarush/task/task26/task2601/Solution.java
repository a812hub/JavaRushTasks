package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);

        int size = array.length;
        int mediana = size % 2 == 1 ? array[size / 2] : (array[size / 2 - 1] + array[size / 2]) / 2;

        Comparator<Integer> comparator = Comparator.comparingInt(o -> Math.abs(o - mediana));

        Arrays.sort(array, comparator);

        return array;
    }
}
