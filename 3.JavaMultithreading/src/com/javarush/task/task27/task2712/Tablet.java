package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private final int number;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public Tablet(int number) {
        this.number = number;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            createOrder0(order);
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
            return null;
        }
    }

    public void createTestOrder() {
        TestOrder order = null;
        try {
            order = new TestOrder(this);
            createOrder0(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");

        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);

        }
    }

    private void createOrder0(Order order) {
        if (!order.isEmpty()) {
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            try {
                queue.put(order);
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }
}
