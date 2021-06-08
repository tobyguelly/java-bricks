package main.game.components;

import java.io.Serializable;

public class Brick implements Serializable {

    private int width, height, x, y, health;

    public Brick(int width, int height, int x, int y, int health) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}