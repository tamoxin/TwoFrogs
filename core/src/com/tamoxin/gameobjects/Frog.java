package com.tamoxin.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marco on 3/27/2016.
 */
public class Frog {

    private Vector2 position;
    private Vector2 velocity;

    private float rotation;
    private int width;
    private int height;

    private float originalX;
    private float originalRightX;

    private boolean isInLeftSide;
    private boolean isAlive;

    private Circle boundingCircle;
    private boolean isEating;
    private boolean wasEaten;


    public Frog(float x, float y, int width, int height) {
        this.height = height;
        this.width = width;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        originalX = x;
        originalRightX = originalX + 34;
        isInLeftSide = true;
        isAlive = true;
        boundingCircle = new Circle();
        isEating = false;
        wasEaten = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x + width/2, position.y + height/2 + 1, width/2 - 1);

        if(position.x <= originalX) {
            velocity.x = 0;
            position.x = originalX;
            rotation = 0;
        }

        if(position.x >= originalRightX) {
            velocity.x = 0;
            position.x = originalRightX;
            rotation = 0;
        }

        if(isGoingLeft()) {
            rotation -= 600 * delta;
            if(rotation < -20)
                rotation = -20;
        }

        if(isGoingRight()) {
            rotation += 600 * delta;
            if(rotation > 20)
                rotation = 20;
        }
    }

    public void onClick() {
        // Cannot tap the frog when game over
        if (this.isAlive()) {
            if (isInLeftSide) {
                velocity.x = 140;
                isInLeftSide = false;
            } else {
                velocity.x = -140;
                isInLeftSide = true;
            }
        }
    }

    public void goLeft() {
        if (this.isAlive()) {
            if (!isInLeftSide) {
                velocity.x = -140;
                isInLeftSide = true;
            }
        }
    }

    public void goRight() {
        if (this.isAlive()) {
            if (isInLeftSide) {
                velocity.x = 140;
                isInLeftSide = false;
            }
        }
    }

    public void onRestart() {
        position.x = originalX;
        rotation = 0;
        velocity.x = 0;
        velocity.y = 0;
        isAlive = true;
        isInLeftSide = true;
        isEating = false;
        wasEaten = false;
    }

    public void setEatingState(boolean state) {
        this.isEating = state;
    }

    public boolean isEating() {
        return isEating;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public boolean wasEaten() {
        return wasEaten;
    }

    public void setEatenState(boolean wasEaten) {
        this.wasEaten = wasEaten;
    }

    public void killFrog(){
        isAlive = false;
    }

    public boolean isGoingRight() {
        return velocity.x > 0;
    }

    public boolean isGoingLeft() {
        return velocity.x < 0;
    }

    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        position.x = x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }
}
