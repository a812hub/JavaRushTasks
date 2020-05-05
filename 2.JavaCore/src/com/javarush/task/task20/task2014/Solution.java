package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws InterruptedException {
        Solution savedObject = new Solution(4);

        try (FileOutputStream fos = new FileOutputStream("D:\\1.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos); ) {
            oos.writeObject(savedObject);
        } catch (IOException e) {}

        Solution loadedObject = new Solution(7);

        try (FileInputStream fis = new FileInputStream("D:\\1.dat");
             ObjectInputStream ois = new ObjectInputStream(fis); ) {
            loadedObject = (Solution) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {}

        System.out.println(savedObject.string);
        System.out.println(loadedObject.string);
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
