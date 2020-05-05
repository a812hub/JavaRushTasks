package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<List<Advertisement>> listsOfAds = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> videos = storage.list().stream().
                filter(a -> a.getHits() > 0).
                filter(a -> a.getDuration() <= timeSeconds).
                collect(Collectors.toList());

        if (videos.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        createListsOfAds(videos);
        Collections.sort(listsOfAds,
                Comparator.comparingLong(this::sumAmount).
                        thenComparingInt(this::time).reversed().
                        thenComparingInt(List::size));

        videos = listsOfAds.get(0);
        Collections.sort(videos, Comparator
                .comparingLong(Advertisement::getAmountPerOneDisplaying).reversed()
                .thenComparingLong(Advertisement::getAmountPerOneSecond));

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(videos, sumAmount(videos), time(videos)));

        for (Advertisement ad : videos) {
            ConsoleHelper.writeMessage(ad.toString());
            ad.revalidate();
        }
    }

    private void createListsOfAds(List<Advertisement> list) {
        if (time(list) <= timeSeconds) {
            listsOfAds.add(list);
            return;
        }
        for (Advertisement advertisement : list) {
            List<Advertisement> copyOfList = new ArrayList<>(list);
            copyOfList.remove(advertisement);
            createListsOfAds(copyOfList);
        }
    }

    private long sumAmount(List<Advertisement> list) {

        long sumAmount = 0;
        for (Advertisement advertisement : list) {
            sumAmount += advertisement.getAmountPerOneDisplaying();
        }
        return sumAmount;
    }

    private int time(List<Advertisement> list) {
        int time = 0;
        for (Advertisement advertisement : list) {
            time += advertisement.getDuration();
        }
        return time;
    }

    private void printList (List<Advertisement> list) {
        System.out.println();
        System.out.println("Печать списка");
        for (Advertisement ad : list) {
            System.out.println(ad.getName() + " длительность " + ad.getDuration() + " цена показа " + ad.getAmountPerOneDisplaying());
        }
        System.out.println("TotalPrise" + sumAmount(list));
        System.out.println("TotalTime" + time(list));
        System.out.println();
    }

}
