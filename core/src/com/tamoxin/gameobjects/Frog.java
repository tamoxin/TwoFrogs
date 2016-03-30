package com.tamoxin.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marco on 3/27/2016.
 */
public class Frog {

    private Vector2 position;
    private Vector2 velocity;
    //private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;
    private float originalX;
    private float originalRightX;

    private boolean isInLeftSide;
    private boolean isDead;

    public Frog(float x, float y, int width, int height) {

        this.height = height;
        this.width = width;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        originalX = x;
        originalRightX = originalX + 34;
        isInLeftSide = true;
        isDead = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

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
        if (isInLeftSide) {
            velocity.x = 140;
            isInLeftSide = false;
        } else {
            velocity.x = -140;
            isInLeftSide = true;
        }
    }

    public boolean isFrogDead(){
        return isDead;
    }

    public void killFrog(){
        isDead = false;
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

    public float getY() {
        return position.y;
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
}
