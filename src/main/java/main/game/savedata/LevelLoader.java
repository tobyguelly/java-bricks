package main.game.savedata;

import main.game.components.Brick;
import main.game.components.Level;
import main.game.utils.Files;

import java.io.*;
import java.util.ArrayList;

public class LevelLoader {

    public static ArrayList<Level> getDefaultLevels() {
        ArrayList<Level> levels = new ArrayList<>();
        int dx = 110, dy = 30;
        for(int i = 0; i < 6; i++) {
            ArrayList<Brick> bricks = new ArrayList<>();
            for(int j = 0; j < i + 1; j++) {
                for(int k = 0; k < 5; k++) {
                    bricks.add(new Brick(100, 20, dx * k, dy * j));
                }
            }
            levels.add(new Level(i + 1, 25 - 5*i + 3, bricks));
        }
        return levels;
    }

    public static void saveLevels(ArrayList<Level> levels) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Files.LEVEL_FILE));
        oos.writeObject(levels);
        oos.flush();
        oos.close();
    }

    public static ArrayList<Level> loadLevels() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Files.LEVEL_FILE));
        ArrayList<Level> levels = (ArrayList<Level>) ois.readObject();
        ois.close();
        return levels;
    }
}