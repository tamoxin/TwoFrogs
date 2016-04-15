package com.tamoxin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Marco on 3/29/2016.
 */
public class Scrollable {

    private Vector2 position;
    private Vector2 velocity;
    private int height;
    private int width;
    private float gameHeight;
    private int id;
    private float originalY;
    private float originalX;
    private Random random;
    private int side;
    private boolean isScrolledDown;
    private boolean itPassedMidPoint;
    private boolean start;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed, int id) {
        gameHeight = Gdx.graphics.getHeight()/(Gdx.graphics.getWidth() / 136);
        this.height = height;
        this.width = width;
        originalY = y;
        originalX = x;

        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);

        itPassedMidPoint = false;
        isScrolledDown = false;
        start = false;

        this.id = id;
        random = new Random();
        setRandomSide();
    }

    public void update(float delta) {

        // Checks if the object should be scrolling
        if(start == false)
            return;

        position.add(velocity.cpy().scl(delta));

        if(position.y + (height/2) > gameHeight/2) {
            itPassedMidPoint = true;
        }

        // If the Scrollable object is no longer visible:
        if (position.y > gameHeight) {
            isScrolledDown = true;
        }
    }

    // Reset should override in subclass for more specific purposes
    public void reset(float newY) {
        setYPosition(newY);
        itPassedMidPoint = false;
        isScrolledDown = false;
        setRandomSide();
        start = false;
        //stop();
    }

    public void start() {
        start = true;
    }

    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public boolean hasPassedMidPoint() { return itPassedMidPoint; }

    public void setXPosition(float newX) {
        position.x = newX;
    }

    public void setYPosition(float newY) {
        position.y = newY;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getOriginalY() { return originalY; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getTailY() {
        return position.y - height;
    }

    public void setVelocityY(float velocityY) {
        velocity.y = velocityY;
    }

    public void stop() {
        velocity.y = 0;
        velocity.x = 0;
    }

    public int getId() {
        return id;
    }

    // Returns in which side is the object
    // Returns 0 when is in the left
    // Returns 1 when is in the right
    public int getSide() {
        return side;
    }

    public void setRandomSide() {
        side = random.nextInt(2);
        if(side == 0)
            setXPosition(originalX);
        else setXPosition(originalX + width + 8);

    }
}
