package com.javarush.task.task25.task2511;

import java.util.Timer;
import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String s = t.getName().replaceAll(".", "*");
                String message = e.getMessage();
                message = message.replace(t.getName(), s);
                System.out.println(message);
            }
        };    //init handler here
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new TimerTask() {
            @Override
            public void run() {
                int a = 0;
                a++;
                a--;
                int i = 5 / a;
            }
        });

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(solution, 0, 1000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        timer.cancel();
    }
}