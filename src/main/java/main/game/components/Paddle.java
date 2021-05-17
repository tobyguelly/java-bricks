package main.game.components;

public class Paddle {

    private int width, height, x, y, delta;

    public Paddle(int width, int height, int x, int y, int delta) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.delta = delta;
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

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
}