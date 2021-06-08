package main.game.main;

import main.game.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class Canvas extends JPanel {

    private Ball ball;
    private Paddle paddle;
    private int score, remaining;
    private ArrayList<Brick> bricks;

    public void reload(Ball ball, Paddle paddle, ArrayList<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
        this.repaint();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(this.ball != null && this.paddle != null) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //Background color
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            //Ball
            g2d.setColor(Color.RED);
            Ellipse2D.Double ball = new Ellipse2D.Double(this.ball.getX(), this.ball.getY(), 2 * this.ball.getRadius(), 2 * this.ball.getRadius());
            g2d.fill(ball);

            paddle.setY(getHeight() - paddle.getHeight() * 2);

            //Paddle
            g2d.setColor(Color.CYAN);
            Rectangle2D.Double paddle = new Rectangle2D.Double(this.paddle.getX(), this.paddle.getY(), this.paddle.getWidth(), this.paddle.getHeight());
            g2d.fill(paddle);

            //All bricks
            for(Brick b : this.bricks) {
                int health = b.getHealth();
                if(health == 1) g2d.setColor(Color.GREEN);
                else if(health == 2) g2d.setColor(Color.YELLOW);
                else g2d.setColor(Color.ORANGE);
                Rectangle2D.Double brick = new Rectangle2D.Double(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                g2d.fill(brick);
            }

            Font font = new Font("Arial", Font.PLAIN, 20);
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);

            if(this.score >= 0) {
                g2d.drawString("Score: " + this.score, 4, 22);
            }
            if(this.remaining >= 0) {
                g2d.drawString("Remaining: " + this.remaining, getWidth() - 130, 22);
            }
        }
    }
}