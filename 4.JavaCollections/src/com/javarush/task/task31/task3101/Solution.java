package com.javarush.task.task31.task3101;

import java.io.*;

import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        FileUtils.deleteFile(resultFileAbsolutePath);

        List<File> files = getFiles(path);

        Collections.sort(files, Comparator.comparing(File::getName));

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(allFilesContent)))) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                    char[] buffer = new char[50];
                    int len = reader.read(buffer);
                    writer.write(buffer, 0, len);
                    writer.write('\n');
                }
            }
        } catch (IOException e) {
            //ntd
        }

    }

    public static List<File> getFiles(File path) {
        List<File> files = new ArrayList<>();
        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else if (file.length() <= 50) {
                files.add(file);
            }
        }
        return files;
    }
}
