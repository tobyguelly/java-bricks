package main.game.main;

import main.game.components.*;
import main.game.savedata.HighscoreLoader;
import main.game.savedata.LevelLoader;
import main.game.savedata.ProgressLoader;
import main.game.utils.Dimensions;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Game implements GameComponents, KeyListener {

    private ArrayList<Level> levels;
    private ArrayList<Brick> bricks;

    private AudioPlayer audioPlayer;

    private int level, speed, score;
    private boolean gameover;

    private Canvas canvas;

    public Game(int level) {
        try {
            this.levels = LevelLoader.loadLevels();
        } catch (Exception e) {
            try {
                LevelLoader.saveLevels(LevelLoader.getDefaultLevels());
            } catch (Exception ignored) {
            }
            this.levels = LevelLoader.getDefaultLevels();
        }
        this.level = level;
        this.gameover = false;
        this.audioPlayer = new AudioPlayer();
        this.speed = levels.get(level - 1).getSpeed();
        this.bricks = levels.get(level - 1).getBricks();

        GameLauncher.setStatusMessage("Du spielst gerade Level " + level + "!");
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(550, 600);
        frame.setTitle("Bricks Level " + level);
        frame.addKeyListener(this);
        frame.setResizable(false);

        audioPlayer.play();

        paddle.setX(frame.getWidth() / 2 - paddle.getWidth() / 2);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                close(frame, "Das Spiel wurde geschlossen!");
            }
        });

        canvas = new Canvas();
        frame.getContentPane().add(canvas);

        frame.setVisible(true);

        while(true) {
            if(!frame.isVisible()) {
                //close(frame, "Das Spiel wurde geschlossen!");
                break;
            }
            ball.setX(ball.getX() + ball.getDeltax());
            ball.setY(ball.getY() + ball.getDeltay());

            //Player has failed, ball is behind paddle
            if((ball.getY() + 2 * ball.getRadius()) > canvas.getHeight()) {
                gameover = true;
                close(frame, "Du hast Level " + level + " verloren! Punke: " + score);
                break;
            }

            //Ball collides with walls, reversing directions
            if(ball.getY() < 0) ball.setDeltay(-ball.getDeltay());
            if((ball.getX() + 2 * ball.getRadius()) > canvas.getWidth()) ball.setDeltax(-ball.getDeltax());
            if(ball.getX() < 0) ball.setDeltax(-ball.getDeltax());

            //Ball collides with paddle
            if((((ball.getY() + 2 * ball.getRadius()) >= paddle.getY()) && ((ball.getY() + 2 * ball.getRadius() <= paddle.getY() + ball.getDeltay()))) && ((ball.getX() + 2 * ball.getRadius() > paddle.getX()) && (ball.getX() < paddle.getX() + paddle.getWidth()))) {
                ball.setDeltay(-ball.getDeltay());
                this.score = this.score + 100;
            }
            ArrayList<Brick> toRemove = new ArrayList<>();
            boolean hasHit = false;
            for(Brick b : this.bricks) {
                //Ball hits paddle
                if((ball.getX() + 2 * ball.getRadius() >= b.getX() && ball.getX() <= b.getX() + b.getWidth()) && (ball.getY() <= b.getY() + b.getHeight() && ball.getY() + 2 * ball.getRadius() >= b.getY())) {
                    hasHit = true;
                    this.score = this.score + 1000;
                    toRemove.add(b);
                }
            }
            //To avoid changing the directory twice (not) if hit two
            if(hasHit) {
                ball.setDeltay(-ball.getDeltay());
            }
            for(Brick b : toRemove) {
                this.bricks.remove(b);
                if(this.bricks.size() == 0) {
                    int playerLevel = 0;
                    try {
                        playerLevel = ProgressLoader.loadLevel();
                    } catch (Exception ignored) {
                    }
                    if(level > playerLevel) {
                        try {
                            ProgressLoader.saveLevel(playerLevel + 1);
                        } catch (Exception ignored) {
                        }
                    }
                    gameover = true;
                    close(frame, "<html><body>Du hast Level " + level + " bestanden! Punke: " + score + ((level > playerLevel) ? "<br>Erfolg: Du hast Level " + (level + 1) + " freigeschalten!": "") + "</body></html>");
                    break;
                }
            }
            canvas.reload(ball, paddle, this.bricks);
            try {
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(paddle.getX() + paddle.getWidth() + 10 >= canvas.getWidth()) return;
            paddle.setX(paddle.getX() + paddle.getDelta());
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(paddle.getX() <= 10) return;
            paddle.setX(paddle.getX() - paddle.getDelta());
        }
    }

    public void close(JFrame frame, String message) {
        audioPlayer.stop();
        GameLauncher.setActive(false);
        GameLauncher.setStatusMessage(message);
        GameLauncher.reloadLevels();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            if(gameover) {
                String name = JOptionPane.showInputDialog(frame, "Speichere deinen Highscore!", "Dein Name");
                if(name != null && name.length() > 1 && name.length() < 30) {
                    HighscoreLoader.saveHighscore(name, this.score);
                } else {
                    HighscoreLoader.saveHighscore(dtf.format(now), this.score);
                }
            }
        } catch (Exception ignored) {
        }
        reset();
        frame.dispose();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void reset() {
        ball.setX(Dimensions.BALL_X);
        ball.setY(Dimensions.BALL_Y);
        ball.setDeltax(Dimensions.BALL_DELTAX);
        ball.setDeltay(Dimensions.BALL_DELTAY);
        paddle.setX(Dimensions.PADDLE_X);
        paddle.setY(Dimensions.PADDLE_Y);
    }
}