package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        List<String> listFiles = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            listFiles.add(args[i]);
        }
        Collections.sort(listFiles);

        Enumeration<FileInputStream> enumeration = new Enumeration<FileInputStream>() {
            List<String> list = listFiles;
            int iterator = 0;

            @Override
            public boolean hasMoreElements() {
                return iterator < list.size();
            }

            @Override
            public FileInputStream nextElement() {
                iterator++;
                try {
                    return new FileInputStream(list.get(iterator - 1));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }
        };

        ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(enumeration));
        FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipInputStream.available() > 0) {
            byte[] buffer = new byte[1024];
            int len = zipInputStream.read(buffer);
            if (len > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
            zipInputStream.close();
            fileOutputStream.close();

    }
}
