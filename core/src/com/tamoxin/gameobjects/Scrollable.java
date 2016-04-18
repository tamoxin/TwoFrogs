package com.tamoxin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import aurelienribon.tweenengine.equations.Circ;

/**
 * Created by Marco on 3/29/2016.
 */
public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int height;
    protected int width;
    protected float gameHeight;
    protected int id;
    protected float originalY;
    protected float originalX;
    protected Random random;
    protected int side;
    protected boolean isScrolledDown;
    protected boolean itPassedMidPoint;
    protected boolean start;
    protected Circle circle;

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

        circle = new Circle();
    }

    public void update(float delta) {

        // Checks if the object should be scrolling
        if(start == false)
            return;

        position.add(velocity.cpy().scl(delta));
        setCircle();

        if(position.y + (height/2) > gameHeight/2) {
            itPassedMidPoint = true;
        }

        // If the Scrollable object is no longer visible:
        if (position.y > gameHeight - 12) {
            isScrolledDown = true;
        }
    }

    // Reset should override in subclass for more specific purposes
    public void reset(float newY) {
        setYPosition(newY);
        setCircle();
        itPassedMidPoint = false;
        isScrolledDown = false;
        start = false;
    }

    public boolean collides(Frog frog) {
        return Intersector.overlaps(frog.getBoundingCircle(), circle);
    }

    public boolean collidesGround(Rectangle ground) {
        return Intersector.overlaps(circle, ground);
    }

    public void start() {
        start = true;
    }

    public boolean isStarted() {
        return start;
    }

    public void scrollDown() {
        this.isScrolledDown = true;
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

    public float getOriginalY() {
        return originalY;
    }

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

    public Circle getCircle() {
        return circle;
    }

    // This method should be overrode for more specific purposes
    protected void setRandomSide() {
        side = random.nextInt(2);
        if(side == 0)
            setXPosition(originalX);
        else setXPosition(originalX + width + 8);
    }

    protected void setCircle(){
        circle.set(position.x + width/2, position.y + height/2, width / 2);
    }
}
