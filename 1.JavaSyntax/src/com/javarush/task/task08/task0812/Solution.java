package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(in.readLine()));
        }
        int l = 0;
        if (list.size() > 1) {
            int tmp = 1;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1).equals(list.get(i))) {
                    tmp++;
                } else {
                    if (tmp > l) {
                        l = tmp;
                    }
                    tmp = 1;
                }
            }
            if (tmp > l) {
                l = tmp;
            }
        }
        System.out.println(l);
    }
}