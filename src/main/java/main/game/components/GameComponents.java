package main.game.components;

import main.game.utils.Dimensions;

public interface GameComponents {
    Ball ball = new Ball((int) Dimensions.BALL_RADIUS, (int) Dimensions.BALL_X, (int) Dimensions.BALL_Y, (int) Dimensions.BALL_DELTAX, (int) Dimensions.BALL_DELTAY);
    Paddle paddle = new Paddle((int) Dimensions.PADDLE_WIDTH, (int) Dimensions.PADDLE_HEIGHT, (int) Dimensions.PADDLE_X, (int) Dimensions.PADDLE_Y, (int) Dimensions.PADDLE_DELTA);

    void reset();
}