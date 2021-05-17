package main.game.savedata;

import main.game.utils.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProgressLoader {

    public static void saveLevel(int level) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Files.PROGRESS_FILE));
        oos.writeObject(level);
        oos.flush();
        oos.close();
    }

    public static int loadLevel() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Files.PROGRESS_FILE));
        int level = (int) ois.readObject();
        ois.close();
        return level;
    }
}