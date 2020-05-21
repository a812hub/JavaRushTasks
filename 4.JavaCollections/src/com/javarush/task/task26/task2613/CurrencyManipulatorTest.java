package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.*;

public class CurrencyManipulatorTest {
    CurrencyManipulator currencyManipulator = new CurrencyManipulator("usd");

    @Test
    public void withdrawAmount_1() throws NotEnoughMoneyException {
        currencyManipulator.addAmount(500, 10);
        currencyManipulator.addAmount(400, 10);
        currencyManipulator.addAmount(300, 10);
//        currencyManipulator.addAmount(100, 1);
//        currencyManipulator.addAmount(50, 12);

        Map<Integer, Integer> result = currencyManipulator.withdrawAmount(1600);
        System.out.println(result);
    }
}