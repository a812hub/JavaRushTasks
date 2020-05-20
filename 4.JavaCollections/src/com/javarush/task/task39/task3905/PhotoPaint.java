package com.javarush.task.task39.task3905;

import java.util.LinkedList;
import java.util.Queue;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {

        if (y > image.length - 1 || x > image[0].length - 1 || x < 0 || y < 0 || image[y][x] == desiredColor)
            return false;

        Color changedColor = image[y][x];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (x + 1 < image[0].length && image[current.y][current.x + 1] == changedColor) {
                queue.add(new Point(current.x + 1, current.y));
            }
            if (x - 1 >= 0 && image[current.y][current.x - 1] == changedColor) {
                queue.add(new Point(current.x - 1, current.y));
            }
            if (y + 1 < image.length && image[current.y + 1][current.x] == changedColor) {
                queue.add(new Point(current.x, current.y + 1));
            }
            if (y - 1 >= 0 && image[current.y - 1][current.x] == changedColor) {
                queue.add(new Point(current.x, current.y - 1));
            }
            image[current.y][current.x] = desiredColor;
        }
        return true;
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
