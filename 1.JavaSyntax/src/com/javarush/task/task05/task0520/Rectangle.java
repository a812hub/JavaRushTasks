package com.javarush.task.task05.task0520;

/* 
Создать класс прямоугольник (Rectangle)
*/


public class Rectangle {
    public int top;
    public int left;
    public int width;
    public int height;

    public Rectangle(int top, int left, int width, int height){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }

    public Rectangle(int top, int left, int width){
        this(top, left, width, width);
    }

    public Rectangle(int top, int left){
        this(top, left, 0,0);
    }

    public Rectangle(Rectangle rectangle){
        this.top = rectangle.top;
        this.left = rectangle.left;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }

    public static void main(String[] args) {

    }
}

