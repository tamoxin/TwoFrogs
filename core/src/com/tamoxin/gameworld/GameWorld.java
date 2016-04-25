package com.tamoxin.gameworld;

import com.badlogic.gdx.math.Rectangle;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameobjects.ScrollHandler;
import com.tamoxin.helpers.AssetLoader;

public class GameWorld {

    private Frog leftFrog, rightFrog;
    private ScrollHandler handler;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private GameState currentState;
    private int bottom;

    public enum GameState {
        MENU, RUNNING, GAMEOVER, HIGHSCORE

    }


    public GameWorld(int bottom) {

        currentState = GameState.MENU;
        ground = new Rectangle(0, bottom - 5,136,5);
        leftFrog = new Frog(2, bottom - 45, 26, 23);
        rightFrog = new Frog(73, bottom - 45, 26, 23);
        handler = new ScrollHandler();
        this.bottom = bottom;
    }

    public void update(float delta) {

        runTime += delta;

        switch (currentState) {
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
        }

    }

    private void updateReady(float delta) {
        leftFrog.update(delta);
        rightFrog.update(delta);
    }

    public void updateRunning(float delta) {

        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        leftFrog.update(delta);
        rightFrog.update(delta);
        handler.update(delta);

        // Check for collisions
        if(leftFrog.isAlive() && rightFrog.isAlive() && handler.collidesWithCrocodile(leftFrog, rightFrog)){
            // Clean up on game over
            handler.stop();
            leftFrog.stop();
            rightFrog.stop();
            AssetLoader.dead.play();

            if(score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            } else {
                currentState = GameState.GAMEOVER;
            }
        } else if(handler.collidesWithFly(leftFrog, rightFrog)) {
            addScore(1);
            AssetLoader.eat.play();
        } else {
            leftFrog.setEatingState(false);
            rightFrog.setEatingState(false);
        }

        if(handler.missFly(ground)){
            handler.stop();
            killFrogs();
            if(score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            } else {
                currentState = GameState.GAMEOVER;
            }
        }

    }

    private void killFrogs() {
        leftFrog.stop();
        rightFrog.stop();
        leftFrog.killFrog();
        rightFrog.killFrog();
    }

    public ScrollHandler getHandler() {
        return handler;
    }

    public Frog getLeftFrog() {
        return leftFrog;
    }

    public Frog getRightFrog() {
        return rightFrog;
    }

    public int getScore() {
        return score;
    }

    public int getBottom() {
        return bottom;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void restart() {
        score = 0;
        leftFrog.onRestart();
        rightFrog.onRestart();
        handler.onRestart();
        currentState = GameState.RUNNING;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }
}
