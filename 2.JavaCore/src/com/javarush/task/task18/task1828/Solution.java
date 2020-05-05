package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static boolean isUTF;
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            if (args[0].equals("-u")) {
                updateProduct(fileName, args[1], getFullProductName(args), args[args.length - 2], args[args.length - 1]);
            } else if (args[0].equals("-d")) {
                deleteProduct(fileName, args[1]);
            }
        }
    }

    public static void deleteProduct(String fileName, String index) throws IOException {
        index = String.format("%-8s", index);
        ArrayList<String> buffer = inputBuffer(fileName);
        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i).startsWith(index)) {
                buffer.remove(i);
                break;
            }
        }
        outputBuffer(fileName, buffer);
    }

    public static void updateProduct
            (String fileName, String index, String productName, String price, String quantity) throws IOException {
        index = String.format("%-8s", index);
        productName = String.format("%-30.30s", productName);
        price = String.format("%-8.8s", price);
        quantity = String.format("%-4.4s", quantity);

        ArrayList<String> buffer = inputBuffer(fileName);
        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i).startsWith(index)) {
                buffer.set(i, index + productName + price + quantity);
                break;
            }
        }

        outputBuffer(fileName, buffer);
    }

    public static void outputBuffer(String fileName, ArrayList<String> buffer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        if (isUTF) writer.write("\uFEFF");
        writer.write(buffer.get(0));
        for (int i = 1; i < buffer.size(); i++) {
            writer.newLine();
            writer.write(buffer.get(i));
        }
        writer.close();
    }

    public static ArrayList<String> inputBuffer(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        ArrayList<String> buffer = new ArrayList<>();
        String currentLine;
        while (reader.ready()) {
            currentLine = reader.readLine();
            if (currentLine.startsWith("\uFEFF")) {
                currentLine = currentLine.substring(1);
                isUTF = true;
            }
            buffer.add(currentLine);
        }
        reader.close();
        return buffer;
    }

    public static String getFullProductName(String[] s) {
        StringBuilder sbProductName = new StringBuilder(s[2]);
        if (s.length > 5) {
            for (int i = 3; i < s.length - 2; i++) {
                sbProductName.append(" ").append(s[i]);
            }
        }
        return sbProductName.toString();
    }
}
/*
D:\3.txt
 */