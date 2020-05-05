package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    //какую сумму заработали на рекламе, сгруппировать по дням;
    public void printAdvertisementProfit() {
        Map<String, Long> adProfit = StatisticManager.getInstance().getAdvertisementProfit();
        long totalAmount = 0L;
        String message;
        for (Map.Entry<String, Long> entry : adProfit.entrySet()) {
            String sDate = entry.getKey();
            long amount = entry.getValue();
            message = String.format("%s - %.2f", sDate, amount / 100d);
            ConsoleHelper.writeMessage(message);
            totalAmount += amount;
        }

        message = String.format("Total - %.2f", totalAmount / 100d);
        ConsoleHelper.writeMessage(message);

    }

    //загрузка (рабочее время) повара, сгруппировать по дням;
    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> cookWork = StatisticManager.getInstance().getCookWorkloading();
        String message;
        for (Map.Entry<String, Map<String, Integer>> entryDateMap : cookWork.entrySet()) {
            String sDate = entryDateMap.getKey();
            ConsoleHelper.writeMessage(sDate);
            for (Map.Entry<String, Integer> entryNameTime : entryDateMap.getValue().entrySet()) {
                message = String.format("%s - %d min",
                        entryNameTime.getKey(), entryNameTime.getValue());
                ConsoleHelper.writeMessage(message);
            }
        }

    }

    //список активных роликов и оставшееся количество показов по каждому;
    public void printActiveVideoSet() {
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().getActiveVideoSet();
        Collections.sort(activeVideo, Comparator.comparing(Advertisement::getName));
        for (Advertisement ad : activeVideo) {
            ConsoleHelper.writeMessage(String.format("%s - %d", ad.getName(), ad.getHits()));
        }
    }

    // список неактивных роликов (с оставшемся количеством показов равным нулю)
    public void printArchivedVideoSet() {
        List<Advertisement> archivedVideo = StatisticAdvertisementManager.getInstance().getArchivedVideoSet();
        Collections.sort(archivedVideo, Comparator.comparing(a -> a.getName().toLowerCase()));
        for (Advertisement ad : archivedVideo) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
