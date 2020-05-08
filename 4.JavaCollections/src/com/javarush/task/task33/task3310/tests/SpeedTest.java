package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTime = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTime = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        int elementsNumber = 10000;
        for (long i = 0; i < elementsNumber; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        Assert.assertTrue(getTimeToGetIds(shortener1, origStrings, ids1) > getTimeToGetIds(shortener2, origStrings, ids2));
        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        Assert.assertEquals(getTimeToGetStrings(shortener1,ids1, strings1), getTimeToGetStrings(shortener2, ids2, strings2), 30);
    }
}
