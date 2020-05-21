package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Comparator.reverseOrder());

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.compute(denomination, (d, c) -> (c == null ? count : c + count));
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream()
                .mapToInt((d) -> d.getKey() * d.getValue())
                .sum();
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> withdrawMap = getWithdrawMap(expectedAmount);

        denominations.entrySet().stream()
                .filter((e) -> withdrawMap.containsKey(e.getKey()))
                .forEach(e -> e.setValue(e.getValue() - withdrawMap.get(e.getKey())));
        denominations.values().removeIf(k -> k == 0);

        return withdrawMap;
    }

    private Map<Integer, Integer> getWithdrawMap(int expectedAmount) throws NotEnoughMoneyException {

        Map<Integer, Integer> bestChoice = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
            if (entry.getKey() == expectedAmount) {
                map.put(entry.getKey(), 1);
                return map;
            }
            if (entry.getKey() < expectedAmount) {
                try {
                    map.putAll(getWithdrawMap(expectedAmount - entry.getKey()));
                } catch (NotEnoughMoneyException ignored) {
                }
                map.compute(entry.getKey(), (k, v) -> v == null ? 1 : v + 1);
            }
            if (getAmount(map) == expectedAmount && isEnoughNotes(map)
                    && (bestChoice.isEmpty() || countOfNotes(bestChoice) > countOfNotes(map))) {
                bestChoice = map;
            }
        }
        if (bestChoice.isEmpty()) throw new NotEnoughMoneyException();
        return bestChoice;
    }

    private int getAmount(Map<Integer, Integer> map){
        return map.entrySet().stream()
                .mapToInt((d) -> d.getKey() * d.getValue())
                .sum();
    }

    private int countOfNotes(Map<Integer, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isEnoughNotes(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (denominations.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
