package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(500);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = new Horse(null, 0, 0);
        for (Horse horse : horses) {
            if (horse.getDistance() > winner.getDistance()) {
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!\n", getWinner().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<>());
        Horse h1 = new Horse("h1", 3, 0);
        Horse h2 = new Horse("h2", 3, 0);
        Horse h3 = new Horse("h3", 3, 0);
        game.getHorses().add(h1);
        game.getHorses().add(h2);
        game.getHorses().add(h3);
        game.run();
        game.printWinner();
    }
}
