package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        final boolean[] isNormal = new boolean[]{false};
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (o1) {
                    thread1.start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    synchronized (o2) {
                        isNormal[0] = true;
                    }
                }
            }
        };
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread2.start();
        Thread.sleep(500);
        return isNormal[0];
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
