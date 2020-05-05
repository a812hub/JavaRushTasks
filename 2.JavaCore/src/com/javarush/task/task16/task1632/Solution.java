package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new FirstThread());
        threads.add(new SecondThread());
        threads.add(new ThirdThread());
        threads.add(new ForthThread());
        threads.add(new FifthThread());
    }

    public static void main(String[] args) throws InterruptedException {

    }

    public static class FirstThread extends Thread {
        @Override
        public void run() {
            while (true) {

            }
        }
    }

    public static class SecondThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class ThirdThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class ForthThread extends Thread implements Message {
        private boolean isCansel = false;
        @Override
        public void run() {
            while (!isCansel) {
            }
        }

        @Override
        public void showWarning() {
            isCansel = true;
        }
    }

    public static class FifthThread extends Thread {
        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
                int sum = 0;
                String s;
                while (!(s = in.readLine()).equals("N")) {
                    sum += Integer.parseInt(s);
                }
                System.out.println(sum);
            } catch (Exception e) {
            }
        }
    }
}