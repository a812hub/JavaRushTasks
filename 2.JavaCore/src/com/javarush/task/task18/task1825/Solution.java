package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> setFiles = new TreeSet<>();
        String fileNames;
        while (!"end".equals(fileNames = reader.readLine())) {
            setFiles.add(fileNames);
        }
        reader.close();
        System.out.println(setFiles);
        String resultFile = null;
        for (String s : setFiles) {
            resultFile = s.substring(0, s.lastIndexOf('.'));
            break;
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(resultFile));
        BufferedInputStream bis;
        for (String s : setFiles) {
             bis = new BufferedInputStream(new FileInputStream(s));
             while (bis.available() > 0) {
                 bos.write(bis.read());
             }
             bis.close();
        }
        bos.close();
    }
}
/*
D:\2.txt.part1
D:\2.txt.part12
D:\2.txt.part13
D:\2.txt.part2
D:\2.txt.part3
D:\2.txt.part11
end
 */