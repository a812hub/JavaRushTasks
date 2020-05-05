package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    private static int priority = 0;

    {
        priority++;
        if (priority > 10) {
            priority = 1;
        }
    }

    public MyThread() {
        setPriority(priority);
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(priority);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priority <= group.getMaxPriority()) {
            setPriority(priority);
        } else {
            setPriority(group.getMaxPriority());
        }
    }

    public MyThread(String name) {
        super(name);
        setPriority(priority);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (priority <= group.getMaxPriority()) {
            setPriority(priority);
        } else {
            setPriority(group.getMaxPriority());
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(priority);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (priority <= group.getMaxPriority()) {
            setPriority(priority);
        } else {
            setPriority(group.getMaxPriority());
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (priority <= group.getMaxPriority()) {
            setPriority(priority);
        } else {
            setPriority(group.getMaxPriority());
        }
    }
}
