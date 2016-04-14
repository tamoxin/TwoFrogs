package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.tamoxin.gameobjects.Frog;

/**
 * Created by Marco on 3/27/2016.
 */
public class InputHandler implements InputProcessor {

    private Frog frogLeft;
    private Frog frogRight;
    private int width;
    private int height;

    public InputHandler(Frog frogLeft, Frog frogRight) {
        this.frogLeft = frogLeft;
        this.frogRight = frogRight;
        width = (int) Gdx.graphics.getWidth();
        height = (int) Gdx.graphics.getHeight();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX < width / 2) {
            frogLeft.onClick();
        } else {
            frogRight.onClick();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
