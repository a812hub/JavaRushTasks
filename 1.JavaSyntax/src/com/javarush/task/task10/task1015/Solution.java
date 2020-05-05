package com.javarush.task.task10.task1015;

import java.util.ArrayList;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() {
        ArrayList<String>[] list = new ArrayList[3];
        ArrayList<String> list0 = new ArrayList<>();
        list0.add("0");
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("11");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("2");
        list[0] = list0;
        list[1] = list1;
        list[2] = list2;
        return list;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}