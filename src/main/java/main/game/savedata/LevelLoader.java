package main.game.savedata;

import main.game.components.Brick;
import main.game.components.Level;
import main.game.utils.Files;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class LevelLoader {

    public static ArrayList<Level> generateDefaultLevels() {
        ArrayList<Level> levels = new ArrayList<>();
        Random random = new Random();
        int dx = 110, dy = 30;
        for(int i = 0; i < 6; i++) {
            ArrayList<Brick> bricks = new ArrayList<>();
            for(int j = 0; j < i + 8; j++) {
                for(int k = 0; k < 5; k++) {
                    if(random.nextInt(10) > j || j < i + 1) {
                        if(!((k == 0 || k == 4) && j < 2)) {
                            bricks.add(new Brick(100, 20, dx * k, dy * j));
                        }
                    }
                }
            }
            levels.add(new Level(i + 1, 20 - 3*(i + 1) + 2, bricks));
        }
        return levels;
    }
}