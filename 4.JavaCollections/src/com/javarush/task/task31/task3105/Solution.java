package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File addFile = new File(args[0]);
        ZipEntry addZipEntry = new ZipEntry("new/" + addFile.getName());

        FileInputStream fileInputStream = new FileInputStream(args[1]);
        Map<String, byte[]> map = new HashMap<>();
        ZipInputStream zis = new ZipInputStream(fileInputStream);
        ZipEntry entry = zis.getNextEntry();
        while (entry != null) {

            if (!entry.getName().equals(addZipEntry.getName())) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }
                map.put(entry.getName(), baos.toByteArray());
                baos.close();
            }
            entry = zis.getNextEntry();
        }
        fileInputStream.close();
        zis.close();

        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
        ZipOutputStream zos = new ZipOutputStream(fileOutputStream);
        for (Map.Entry<String, byte[]> mapEntry : map.entrySet()) {
            zos.putNextEntry(new ZipEntry(mapEntry.getKey()));
            zos.write(mapEntry.getValue());
        }
        zos.putNextEntry(addZipEntry);
        Files.copy(addFile.toPath(), zos);
        fileOutputStream.close();
        zos.close();

    }
}
