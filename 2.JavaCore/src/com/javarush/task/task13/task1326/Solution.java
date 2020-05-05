package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String s = in.readLine();
            try (BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(s)))) {
                while ((s = inFile.readLine()) != null) {
                    String[] ss;
                    ss = s.split(" ");
                    for (String s1 : ss) {
                        list.add(Integer.parseInt(s1));
                    }
                }

            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        Collections.sort(list);
        for (int i : list) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}


//  D:\1.txt

/*
public class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String s = in.readLine();
            try (BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(s))) {
                int n;
                String ss = "";
                while (inFile.available() > 0) {
                    n = inFile.read();
                    if (n == 32) {
                        list.add(Integer.parseInt(ss));
                        ss = "";
                    } else if (n == 13) {
                        inFile.read();
                        list.add(Integer.parseInt(ss));
                        ss = "";
                    } else if (inFile.available() == 0) {
                        ss += (char) n;
                        list.add(Integer.parseInt(ss));
                    }
                    else {
                        ss += (char) n;
                    }
                }

            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        System.out.println(list);
        Collections.sort(list);
        for (int i : list) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
*/