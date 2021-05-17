package main.game.savedata;

import main.game.utils.Files;

import java.io.*;
import java.util.HashMap;

public class HighscoreLoader {

    public static HashMap<String, Integer> scores;

    static {
        try {
            scores = loadHighscores();
        } catch (Exception e) {
            scores = new HashMap<>();
        }
    }

    public static void saveHighscore(String name, int score) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Files.HIGHSCORE_FILE));
        if(scores.size() < 1) scores = new HashMap<>();
        scores.put(name, score);
        oos.writeObject(scores);
        oos.flush();
        oos.close();
    }

    public static HashMap<String, Integer> loadHighscores() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Files.HIGHSCORE_FILE));
        Object scores = ois.readObject();
        ois.close();
        return (HashMap<String, Integer>) scores;
    }

    public static HashMap<String, Integer> getScores() {
        return HighscoreLoader.scores;
    }
}