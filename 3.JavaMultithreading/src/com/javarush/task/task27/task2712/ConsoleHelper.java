package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage("Выберете блюдо: Fish, Steak, Soup, Juice, Water. Для завершения заказа введите exit.");
        List<Dish> allDishes = new ArrayList<>();

        String dish = readString();
        while (!dish.equals("exit")) {
            switch (dish) {
                case "Fish":
                    allDishes.add(Dish.Fish);
                    break;
                case "Steak":
                    allDishes.add(Dish.Steak);
                    break;
                case "Soup":
                    allDishes.add(Dish.Soup);
                    break;
                case "Juice":
                    allDishes.add(Dish.Juice);
                    break;
                case "Water":
                    allDishes.add(Dish.Water);
                    break;
                default:
                    writeMessage("Такого блюда нет.");
            }
            dish = readString();
        }
        return allDishes;
    }
}
