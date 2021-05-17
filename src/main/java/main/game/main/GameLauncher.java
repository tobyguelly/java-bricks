package main.game.main;

import main.game.dialogs.HighscoreDialog;
import main.game.savedata.LevelLoader;
import main.game.savedata.ProgressLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher implements ActionListener {

    private volatile JFrame frame;
    private volatile JLabel status;
    private JPanel buttons;
    private JPanel options;
    private JButton sound;
    private JButton highscores;
    private boolean active = false;
    public static GameLauncher LAUNCHER;

    public GameLauncher() {
        frame = new JFrame("Bricks Launcher");
        frame.setSize(300, 250);
        frame.setLocation(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(3);
        frame.setLayout(layout);
        status = new JLabel("<html><body>Willkommen bei Bricks!<br>Bitte wähle einen Level aus!</body></html>");
        frame.add(status);
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        frame.add(buttons);
        options = new JPanel();
        options.setLayout(new FlowLayout());
        frame.add(options);
        status.setHorizontalAlignment(JTextField.CENTER);
        sound = new JButton("Sound ON");
        sound.addActionListener(new AudioPlayer(true));
        options.add(sound);
        highscores = new JButton("Show Highscores");
        highscores.addActionListener(new HighscoreDialog(frame));
        options.add(highscores);
        LAUNCHER = this;
    }

    public void run() {
        buttons.removeAll();
        int playerLevel = 0;
        try {
            playerLevel = ProgressLoader.loadLevel();
        } catch (Exception ignored) {
        }
        for (int i = 1; i <= Math.min(LevelLoader.getDefaultLevels().size(), playerLevel + 1); i++) {
            JButton button = new JButton("Level " + i);
            buttons.add(button);
            button.addActionListener(this);
            button.addActionListener(e -> {
                //frame.dispose();
                if(!this.active) {
                    new Thread(() -> {
                        String buttonText = ((JButton) e.getSource()).getText();
                        int level = Integer.parseInt(buttonText.substring(buttonText.length() - 1));
                        this.status.setText("Spiel lädt...");
                        Game game = new Game(level);
                        game.run();
                    }).start();
                }
            });
        }
        buttons.revalidate();
        buttons.repaint();
        frame.revalidate();
        frame.repaint();
    }

    public static void setStatusMessage(String message) {
        LAUNCHER.status.setText(message);
    }

    public static void setActive(boolean active) {
        LAUNCHER.active = active;
    }

    public static void reloadLevels() {
        LAUNCHER.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.active) {
            this.status.setText("Es läuft bereits ein Spiel!");
        } else {
            String button = ((JButton) e.getSource()).getText();
            try {
                int level = Integer.parseInt(button.substring(button.length() - 1));
                this.status.setText("Du spielst gerade Level " + level + "!");
                this.active = true;
            } catch(Exception ignored) {
            }
        }
    }
}