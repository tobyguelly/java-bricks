package main.game.components;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {

    private int level, speed;
    private ArrayList<Brick> bricks;

    public Level(int level, int speed, ArrayList<Brick> bricks) {
        this.level = level;
        this.speed = speed;
        this.bricks = bricks;
    }

    public int getLevel() {
        return level;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}