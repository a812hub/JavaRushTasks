package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static void main(String[] args) throws InterruptedException {

        Cook cookGun = new Cook("Gun");
        cookGun.setQueue(orderQueue);
        Cook cookLeo = new Cook("Leo");
        cookLeo.setQueue(orderQueue);

//        Thread threadGun = new Thread(cookGun, "Gun");
//        Thread threadLeo = new Thread(cookGun, "Leo");
//        threadGun.start();
//        threadLeo.start();
        executorService.execute(cookGun);
        executorService.execute(cookLeo);

        Waiter waiter = new Waiter();
        cookGun.addObserver(waiter);
        cookLeo.addObserver(waiter);


        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i).setQueue(orderQueue);
        }

        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomOrderGeneratorTask);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

//        threadGun.join(500);
//        threadLeo.join(500);
//        threadGun.interrupt();
//        threadLeo.interrupt();


        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdownNow();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }

}

