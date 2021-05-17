package main.game.components;

public class Ball {

    private double x, y, radius, deltax, deltay;

    public Ball(double radius, double x, double y, double deltax, double deltay) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.deltax = deltax;
        this.deltay = deltay;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDeltax() {
        return deltax;
    }

    public void setDeltax(double deltax) {
        this.deltax = deltax;
    }

    public double getDeltay() {
        return deltay;
    }

    public void setDeltay(double deltay) {
        this.deltay = deltay;
    }
}