package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Thread> listOfThreads = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        while (!"exit".equals(s)) {
            Thread thread = new ReadThread(s);
            listOfThreads.add(thread);
            thread.start();
            s = reader.readLine();
        }
        reader.close();
        for (Thread thread : listOfThreads) {
            thread.join();
        }
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            super(fileName);
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try (FileInputStream fis = new FileInputStream(fileName)) {
                ArrayList<Integer> list = new ArrayList<>();
                while (fis.available() > 0) {
                    list.add(fis.read());
                }
                int countMax = 0;
                int intMax = 0;
                for (int i : list) {
                    if (Collections.frequency(list, i) > countMax) {
                        countMax = Collections.frequency(list, i);
                        intMax = i;
                    }
                }
                synchronized (resultMap) {
                    resultMap.put(fileName, intMax);
                }
            } catch (IOException e) {}
        }
    }
}

/*
D:\1.txt
D:\2.txt
D:\3.txt
D:\4.txt
exit
 */