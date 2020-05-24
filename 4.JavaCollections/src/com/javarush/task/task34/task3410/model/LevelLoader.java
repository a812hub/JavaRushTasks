package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LevelLoader {
    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = (level - 1) % 60 + 1;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        try {
            List<String> ss = Files.readAllLines(levels);
            int k = 0;
            while (!ss.get(k).equals("Maze: " + level)) {
                k++;
            }
            int xSize = Integer.parseInt(ss.get(k + 2).substring(8));
            int ySize = Integer.parseInt(ss.get(k + 3).substring(8));

            for (int j = 0; j < ySize; j++) {
                String line = ss.get(k + 7 + j);
                for (int i = 0; i < xSize; i++) {
                    int x = Model.FIELD_CELL_SIZE / 2 + Model.FIELD_CELL_SIZE * i;
                    int y = Model.FIELD_CELL_SIZE / 2 + Model.FIELD_CELL_SIZE * j;
                    switch (line.charAt(i)) {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '&':
                            homes.add(new Home(x, y));
                            boxes.add(new Box(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}