package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    public void initDishes() throws IOException {
        this.dishes = new ArrayList<>();
        int countDishes = (int) (Math.random() * 5);
        Dish[] dishValues = Dish.values();
        for (int i = 0; i < countDishes; i++) {
            this.dishes.add(dishValues[(int) (Math.random() * 5)]);
        }
    }
}
