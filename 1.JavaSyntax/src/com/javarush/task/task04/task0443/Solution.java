package com.javarush.task.task04.task0443;

/* 
Как назвали, так назвали
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        String y = reader.readLine();
        String m = reader.readLine();
        String d = reader.readLine();
        System.out.printf("Меня зовут %s.\nЯ родился %s.%s.%s", name, d, m, y);
    }
}
