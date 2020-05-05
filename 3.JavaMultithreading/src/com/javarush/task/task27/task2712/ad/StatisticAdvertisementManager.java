package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static volatile StatisticAdvertisementManager instance;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (instance == null) {
            synchronized (StatisticAdvertisementManager.class) {
                if (instance == null) {
                    instance = new StatisticAdvertisementManager();
                }
            }
        }
        return instance;
    }

    public List<Advertisement> getActiveVideoSet() {
        return advertisementStorage.list().stream().filter(a -> a.getHits() > 0).collect(Collectors.toList());
    }

    public List<Advertisement> getArchivedVideoSet() {
        return advertisementStorage.list().stream().filter(a -> a.getHits() == 0).collect(Collectors.toList());
    }
}
