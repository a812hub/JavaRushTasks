package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        Thread.State state = thread.getState();
        System.out.println(state);
        do {
            Thread.State state1 = thread.getState();
            if (state != state1) {
                System.out.println(state1);
                state = state1;
            }
        } while (state != Thread.State.TERMINATED);
    }
}
