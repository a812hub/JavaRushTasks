package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        ArrayList<Throwable> list = new ArrayList<>();
        list.add(e);
        while (e.getCause() != null) {
            e = e.getCause();
            list.add(e);
        }
        Collections.reverse(list);
        for (Throwable throwable : list) {
//            System.out.println(throwable.getClass().getName() + ": " + throwable.getMessage());
            System.out.println(throwable);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                throw new RuntimeException("DEF", new NullPointerException("GHI"));
            }
        };
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }
}
