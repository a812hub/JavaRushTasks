package com.javarush.task.task07.task0716;

import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings.add("лось");
        strings.add("рысь");
        strings.add("рыло");
        strings = fix(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); ) {
            if (strings.get(i).indexOf('р') >= 0 && strings.get(i).indexOf('л') < 0) {
                strings.remove(i);
            } else if (strings.get(i).indexOf('р') < 0 && strings.get(i).indexOf('л') >= 0) {
                strings.add(i, strings.get(i));
                i += 2;
            } else {
                i++;
            }
        }
        return strings;
    }
}