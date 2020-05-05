package com.javarush.task.task05.task0519;

/* 
Ходим по кругу
*/

public class Circle {
    private int centerX;
    private int centerY;
    private int radius;
    private int width;
    private int color;

    public Circle(int centerX, int centerY, int radius, int width, int color){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.width = width;
        this.color = color;
    }

    public Circle(int centerX, int centerY, int radius, int width){
        this(centerX, centerY, radius, width,0);
    }

    public Circle(int centerX, int centerY, int radius){
        this(centerX, centerY, radius, 0,0);
    }

    public static void main(String[] args) {

    }
}