package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws IOException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws Exception {
        out.defaultWriteObject();
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("D:\\1.dat");
        solution.writeObject("first");
        solution.close();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\2.dat"));
        oos.writeObject(solution);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\2.dat"));
        Solution solution1 = (Solution)ois.readObject();
        ois.close();
        solution1.writeObject("second");
        solution1.close();
    }
}
