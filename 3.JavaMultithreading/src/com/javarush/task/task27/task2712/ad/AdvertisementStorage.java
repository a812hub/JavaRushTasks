package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementStorage {
    private static volatile AdvertisementStorage instance;

    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "video 1", 20000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "aVideo 2", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Еще видео", 400, 2, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Video 4", 800, 1, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "bVideo 5", 800, 2, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Шесть", 1000, 10, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Видео 8", 800, 4, 10 * 60)); //10 min
    }

    public static AdvertisementStorage getInstance() {
        if (instance == null) {
            synchronized (AdvertisementStorage.class) {
                if (instance == null) {
                    instance = new AdvertisementStorage();
                }
            }
        }
        return instance;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
