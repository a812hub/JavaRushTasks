package com.javarush.task.task03.task0322;

/* 
Большая и чистая
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name1 = br.readLine();
        String name2 = br.readLine();
        String name3 = br.readLine();
        System.out.printf("%s + %s + %s = Чистая любовь, да-да!", name1, name2, name3);
    }
}