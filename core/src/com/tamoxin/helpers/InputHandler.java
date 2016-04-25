package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameworld.GameWorld;
import com.tamoxin.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements InputProcessor {

    private Frog leftFrog;
    private Frog rightFrog;
    private GameWorld gameWorld;

    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;
    private SimpleButton restartButton;

    private float scaleFactorX;
    private float scaleFactorY;
    private float screenX;

    private int width;
    private int height;

    public InputHandler(GameWorld gameWorld, float scaleFactorX, float scaleFactorY) {

        this.gameWorld = gameWorld;
        rightFrog = gameWorld.getRightFrog();
        leftFrog = gameWorld.getLeftFrog();

        width = (int) Gdx.graphics.getWidth();
        height = (int) Gdx.graphics.getHeight();
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                (gameWorld.getBottom()/2), 32, 32, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        restartButton = new SimpleButton(
                136 / 2 - (AssetLoader.restartButtonUp.getRegionWidth() / 2),
                (gameWorld.getBottom()/2), 32, 32, AssetLoader.restartButtonUp,
                AssetLoader.restartButtonDown);
        menuButtons.add(playButton);
        menuButtons.add(restartButton);

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                leftFrog.goLeft();
                break;
            case Input.Keys.S:
                leftFrog.goRight();
                break;
            case Input.Keys.LEFT:
                rightFrog.goLeft();
                break;
            case Input.Keys.RIGHT:
                rightFrog.goRight();
                break;
            case Input.Keys.SPACE:
                if(gameWorld.isMenu()) {
                    gameWorld.start();
                }

                if(gameWorld.isGameOver() || gameWorld.isHighScore()) {
                    gameWorld.restart();
                }
                break;
        }

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

        this.screenX = screenX;
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        System.out.println(screenX + " " + screenY);
        if(gameWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        }

        if(gameWorld.isRunning()) {
            if (this.screenX < width / 2) {
                leftFrog.onClick();
            } else {
                rightFrog.onClick();
            }
        }

        if(gameWorld.isGameOver() || gameWorld.isHighScore()) {
            restartButton.isTouchDown(screenX, screenY);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if(gameWorld.isMenu()) {
            if(playButton.isTouchUp(screenX, screenY)){
                gameWorld.start();
                return true;
            }
        } else if(gameWorld.isGameOver() || gameWorld.isHighScore()) {
            if(restartButton.isTouchUp(screenX,screenY)){
                gameWorld.restart();
                return true;
            }
        }
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

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY/ scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
}
