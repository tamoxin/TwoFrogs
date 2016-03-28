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

    private boolean isInLeftSide;
    private boolean isInRightSide;

    public Frog(float x, float y, int width, int height) {
        this.height = height;
        this.width = width;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        //acceleration = new Vector2(460, 0);
        isInLeftSide = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
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
