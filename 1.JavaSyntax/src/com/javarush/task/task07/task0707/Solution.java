package com.javarush.task.task07.task0707;

import java.util.ArrayList;

/* 
Что за список такой?
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Cat");
        list.add("Dog");
        list.add("Revision");
        list.add("Odd");
        list.add("Even");
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }
    }
}
