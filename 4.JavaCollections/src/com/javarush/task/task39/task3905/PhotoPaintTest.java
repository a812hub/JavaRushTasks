package com.javarush.task.task39.task3905;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoPaintTest {

    @Test
    public void paintFill_1() {
        Color[][] image = new Color[][]{
                {Color.BLUE,    Color.BLUE, Color.BLUE},
                {Color.BLUE,    Color.BLUE, Color.BLUE},
                {Color.BLUE,    Color.BLUE, Color.BLUE},
                {Color.BLUE,    Color.RED,  Color.BLUE},
                {Color.BLUE,    Color.RED,  Color.RED},
                {Color.BLUE,    Color.BLUE, Color.BLUE}
        };
        print(image);
        new PhotoPaint().paintFill(image, 2, 4, Color.GREEN);
        print(image);
    }

    public void print(Color[][] image){
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}