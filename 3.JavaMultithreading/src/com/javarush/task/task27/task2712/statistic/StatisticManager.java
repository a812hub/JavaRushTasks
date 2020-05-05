package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static volatile StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if (instance == null) {
            synchronized (StatisticManager.class) {
                if (instance == null) {
                    instance = new StatisticManager();
                }
            }
        }
        return instance;
    }

    public Map<String, Long> getAdvertisementProfit() {
        List<EventDataRow> videoStorage = statisticStorage.storage.get(EventType.SELECTED_VIDEOS);
        Map<String, Long> result = new TreeMap<>(Comparator.reverseOrder());

        for (EventDataRow eventDataRow : videoStorage) {
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) eventDataRow;
            Date date = videoEvent.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String sDate = simpleDateFormat.format(date);

            result.merge(sDate, videoEvent.getAmount(), Long::sum);
        }

//
//        try {
//            SimpleDateFormat fakeSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date fakeDate = fakeSimpleDateFormat.parse("06-03-2020");
//            result.put(fakeDate, 20L);
//        } catch (ParseException e) {
//        }
//

        return result;
    }

    public Map<String, Map<String, Integer>> getCookWorkloading() {
        List<EventDataRow> orderStorage = statisticStorage.storage.get(EventType.COOKED_ORDER);
        Map<String, Map<String, Integer>> result = new TreeMap<>(Comparator.reverseOrder());

        for (EventDataRow eventDataRow : orderStorage) {
            CookedOrderEventDataRow orderEvent = (CookedOrderEventDataRow) eventDataRow;
            Date date = orderEvent.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String sDate = simpleDateFormat.format(date);


            Map<String, Integer> cookTimeTemp = result.get(sDate);
            if (cookTimeTemp != null) {
                cookTimeTemp.merge(orderEvent.getCookName(), orderEvent.getTime(), Integer::sum);
            } else {
                cookTimeTemp = new TreeMap<>();
                cookTimeTemp.put(orderEvent.getCookName(), orderEvent.getTime());
            }

            result.put(sDate, cookTimeTemp);
        }

//
//        try {
//            SimpleDateFormat fakeSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Map<String, Integer> fakeCookTime = new TreeMap<>();
//            Date fakeDate = fakeSimpleDateFormat.parse("06-03-2020");
//            fakeCookTime.put("Gun", 30);
//            fakeCookTime.put("Leo", 30);
//            result.put(fakeDate, fakeCookTime);
//        } catch (ParseException e) {
//        }

        return result;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<EventDataRow>());
            }
        }


        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

    }
}
