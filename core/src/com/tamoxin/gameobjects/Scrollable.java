package com.tamoxin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marco on 3/29/2016.
 */
public class Scrollable {

    Vector2 position;
    Vector2 velocity;
    int height;
    int width;
    boolean isScrolledDown;
    float gameHeight;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        this.height = height;
        this.width = width;
        isScrolledDown = false;
        gameHeight = Gdx.graphics.getHeight()/(Gdx.graphics.getWidth() / 136);
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if(position.y - height > gameHeight)
            isScrolledDown = true;
    }

    // Reset should override in subclass for more specific purposes
    public void reset(float newY) {
        position.y = newY;
        isScrolledDown = false;
    }

    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
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
}
