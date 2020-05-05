package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br2 = new BufferedReader(new FileReader(br1.readLine()))) {
            ArrayList<String> list = new ArrayList<>();
            while (br2.ready()) {
                String[] ss = br2.readLine().split(" ");
                list.addAll(Arrays.asList(ss));
            }
            String[] ss = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ss[i] = list.get(i);
            }
            StringBuilder result = getLine(ss);
            System.out.println(result.toString());

        } catch (IOException e){}

    }

    public static StringBuilder getLine(String... words) {
        if (words.length > 0) {
            ArrayList<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(words));
            ArrayList<String> result = new ArrayList<>();
            result.add(list.get(0));
            list.remove(0);
            int j = 0;
            while (j < list.size()) {
                if (list.get(j).toLowerCase().charAt(list.get(j).length() - 1) == result.get(0).toLowerCase().charAt(0)) {
                    result.add(0, list.get(j));
                    list.remove(j);
                    j = 0;
                } else if (list.get(j).toLowerCase().charAt(0) ==
                        result.get(result.size() - 1).toLowerCase().charAt(result.get(result.size() - 1).length() - 1)) {
                    result.add(list.get(j));
                    list.remove(j);
                    j = 0;
                } else {
                    j++;
                }
            }
            while (list.size() > 0) {
                for (int i = 1; i < result.size(); i++) {
                    if (list.get(0).toLowerCase().charAt(list.get(0).length() - 1) == result.get(i).toLowerCase().charAt(0) &&
                            list.get(0).toLowerCase().charAt(0) ==
                                    result.get(i - 1).toLowerCase().charAt(result.get(i - 1).length() - 1)) {
                        result.add(i, list.get(0));
                        list.remove(0);
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String s : result) {
                sb.append(s);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb;
        } else {
            return new StringBuilder();
        }
    }

}
/*
D:\2.txt
 */