package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        BufferedReader fr = new BufferedReader(new FileReader(fileName1));
        ArrayList<String> linesFile1 = new ArrayList<>();
        while (fr.ready()) {
            linesFile1.add(fr.readLine());
        }
        fr.close();
        fr = new BufferedReader(new FileReader(fileName2));
        ArrayList<String> linesFile2 = new ArrayList<>();
        while (fr.ready()) {
            linesFile2.add(fr.readLine());
        }
        fr.close();

        for (int i = 0, j = 0; i < linesFile1.size() || j < linesFile2.size(); ) {
            if (i >= linesFile1.size()) {
                lines.add(new LineItem(Type.ADDED, linesFile2.get(j)));
                j++;
            } else if (j >= linesFile2.size()) {
                lines.add(new LineItem(Type.REMOVED, linesFile1.get(i)));
                i++;
            } else if (linesFile1.get(i).equals(linesFile2.get(j))) {
                lines.add(new LineItem(Type.SAME, linesFile1.get(i)));
                i++;
                j++;
            } else if (linesFile1.get(i).equals(linesFile2.get(j + 1))) {
                lines.add(new LineItem(Type.ADDED, linesFile2.get(j)));
                lines.add(new LineItem(Type.SAME, linesFile1.get(i)));
                i++;
                j += 2;
            } else if (linesFile1.get(i + 1).equals(linesFile2.get(j))) {
                lines.add(new LineItem(Type.REMOVED, linesFile1.get(i)));
                lines.add(new LineItem(Type.SAME, linesFile1.get(i + 1)));
                i += 2;
                j++;
            }
        }
//        for (LineItem lineItem : lines) {
//            System.out.println(lineItem.type + " " + lineItem.line);
//        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}

/*
D:\3.txt
D:\4.txt
 */