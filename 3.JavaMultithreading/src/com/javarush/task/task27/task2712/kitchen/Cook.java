package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy = false;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();


    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        int cookingTime = order.getTotalCookingTime();
        String message = "Start cooking - " + order.toString() +
                ", cooking time " + cookingTime + "min";
        ConsoleHelper.writeMessage(message);
        StatisticManager.getInstance().register
                (new CookedOrderEventDataRow(order.getTablet().toString(), name, cookingTime, order.getDishes()));
        try {
            Thread.sleep(cookingTime * 10);
        } catch (InterruptedException e) {
            //ntd
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!queue.isEmpty()) {
                    if (!busy) {
                        startCookingOrder(queue.take());
                    }
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
