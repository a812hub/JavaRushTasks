package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        int x = this.getX();
        int y = this.getY();
        switch (direction) {
            case UP:
                y -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                y += Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                x += Model.FIELD_CELL_SIZE;
                break;
            case LEFT:
                x -= Model.FIELD_CELL_SIZE;
        }
        return (x == gameObject.getX() && y == gameObject.getY());
    }
}
