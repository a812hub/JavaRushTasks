package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            ArrayList<String> list = new ArrayList<>();
            while (fileReader.ready()) {
                String[] ss = fileReader.readLine().split(" ");
                list.addAll(Arrays.asList(ss));
            }
            for (int i = 0; i < list.size() - 1; i++) {
                StringBuilder sb = new StringBuilder(list.get(i));
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).equals(sb.reverse().toString())) {
                        Pair pair = new Pair();
                        pair.first = list.get(i);
                        pair.second = list.get(j);
                        if (!result.contains(pair)) {
                            result.add(pair);
                        }
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        for (Pair pair : result) {
            System.out.println(pair);
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
/*
D:\2.txt
 */