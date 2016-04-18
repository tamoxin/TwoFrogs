package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameobjects.ScrollHandler;
import com.tamoxin.helpers.AssetLoader;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameWorld {

    private Frog leftFrog, rightFrog;
    private ScrollHandler handler;
    private Rectangle ground;
    private int score = 0;

    public GameWorld(int bottom) {

        ground = new Rectangle(0, bottom - 5,136,5);
        leftFrog = new Frog(2, bottom - 45, 26, 23);
        rightFrog = new Frog(73, bottom - 45, 26, 23);
        handler = new ScrollHandler();
    }

    public void update(float delta) {

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

    public void addScore(int increment) {
        score += increment;
    }

}
