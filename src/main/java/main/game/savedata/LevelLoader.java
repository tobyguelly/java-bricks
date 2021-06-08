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
        for(int level = 0; level < 6; level++) {
            ArrayList<Brick> bricks = new ArrayList<>();
            for(int row = 1; row < level + 8; row++) {
                for(int col = 0; col < 5; col++) {
                    if(random.nextInt(10) > row || row < level + 1) {
                        if(!((col == 0 || col == 4) && row < 2)) {
                            int maxHealth = 3;
                            if(level < 3) maxHealth = 2;
                            if(level == 0) maxHealth = 1;
                            bricks.add(new Brick(100, 20, dx * col, dy * row, 1 + (int) (Math.random() * maxHealth)));
                        }
                    }
                }
            }
            levels.add(new Level(level + 1, 9 - (level + 1), bricks));
        }
        return levels;
    }
}