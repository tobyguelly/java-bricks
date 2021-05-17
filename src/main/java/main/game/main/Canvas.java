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
    private ArrayList<Brick> bricks;

    public void reload(Ball ball, Paddle paddle, ArrayList<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        if(this.ball != null && this.paddle != null) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.RED);
            Ellipse2D.Double ball = new Ellipse2D.Double(this.ball.getX(), this.ball.getY(), 2 * this.ball.getRadius(), 2 * this.ball.getRadius());
            g2d.fill(ball);

            paddle.setY(getHeight() - paddle.getHeight() * 2);

            g2d.setColor(Color.CYAN);
            Rectangle2D.Double paddle = new Rectangle2D.Double(this.paddle.getX(), this.paddle.getY(), this.paddle.getWidth(), this.paddle.getHeight());
            g2d.fill(paddle);

            g2d.setColor(Color.GREEN);
            for(Brick b : this.bricks) {
                Rectangle2D.Double brick = new Rectangle2D.Double(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                g2d.fill(brick);
            }
        }
    }
}