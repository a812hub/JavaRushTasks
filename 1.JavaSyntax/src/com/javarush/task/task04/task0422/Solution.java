package com.javarush.task.task04.task0422;

/* 
18+
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = in.nextInt();
        if (n < 18) System.out.println("Подрасти еще");
        in.close();
    }
}
