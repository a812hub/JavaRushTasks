package com.javarush.task.task17.task1721;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            allLines = Files.readAllLines(Paths.get(s1));
            forRemoveLines = Files.readAllLines(Paths.get(s2));
            if (allLines.get(0).charAt(0) == '\uFEFF') {
                allLines.set(0, allLines.get(0).substring(1));
            }
            if (forRemoveLines.get(0).charAt(0) == '\uFEFF') {
                forRemoveLines.set(0, forRemoveLines.get(0).substring(1));
            }
        } catch (IOException e) {
        }
        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
        }
    }

    public void joinData() throws CorruptedDataException {
        ArrayList<String> tmpAllLines = new ArrayList<>(allLines);
        if (tmpAllLines.containsAll(forRemoveLines)) {
            tmpAllLines.removeAll(forRemoveLines);
            allLines = tmpAllLines;
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}

/*
D:\1.txt
D:\2.txt
 */