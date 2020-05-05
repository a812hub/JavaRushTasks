package com.javarush.task.task16.task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        SpecialThread st1 = new SpecialThread();
        Thread th1 = new Thread(st1);
        list.add(th1);
        SpecialThread st2 = new SpecialThread();
        Thread th2 = new Thread(st2);
        list.add(th2);
        SpecialThread st3 = new SpecialThread();
        Thread th3 = new Thread(st3);
        list.add(th3);
        SpecialThread st4 = new SpecialThread();
        Thread th4 = new Thread(st4);
        list.add(th4);
        SpecialThread st5 = new SpecialThread();
        Thread th5 = new Thread(st5);
        list.add(th5);
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}
