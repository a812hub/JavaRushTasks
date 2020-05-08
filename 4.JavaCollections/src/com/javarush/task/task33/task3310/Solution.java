package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static void main(String[] args) {

        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new OurHashMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new FileStorageStrategy();
        testStrategy(strategy, 10);
        strategy = new OurHashBiMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new HashBiMapStorageStrategy();
        testStrategy(strategy, 10000);
        strategy = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy, 10000);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for (String value : strings) {
            result.add(shortener.getId(value));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long key : keys) {
            result.add(shortener.getString(key));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> values = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            values.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date startTime = new Date();
        Set<Long> keys = getIds(shortener, values);
        Date finishTime = new Date();
        Helper.printMessage(String.valueOf(finishTime.getTime() - startTime.getTime()));
        startTime = new Date();
        Set<String> resultValues = getStrings(shortener, keys);
        finishTime = new Date();
        Helper.printMessage(String.valueOf(finishTime.getTime() - startTime.getTime()));
        if (values.equals(resultValues)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
        Helper.printMessage("---------------------------------");
    }
}
