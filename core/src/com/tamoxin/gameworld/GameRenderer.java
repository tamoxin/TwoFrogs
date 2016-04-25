package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;

import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameobjects.ScrollHandler;
import com.tamoxin.gameobjects.Scrollable;
import com.tamoxin.helpers.InputHandler;
import com.tamoxin.tweenaccessors.Value;
import com.tamoxin.tweenaccessors.ValueAccessor;
import com.tamoxin.helpers.AssetLoader;
import com.tamoxin.ui.SimpleButton;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private int gameHeight;

    // Game Objects
    private Frog leftFrog;
    private Frog rightFrog;
    private Queue <Scrollable> leftElements, rightElements;
    private ScrollHandler handler;

    // Game Assets
    private Animation background;
    private TextureRegion leftFrogEaten, rightFrogEaten;
    private Animation leftFrogAnimation, rightFrogAnimation, rightCrocodileAnimation;
    private Animation leftCrocodileAnimation, leftFlyAnimation, rightFlyAnimation;
    private Animation leftAnimation, rightAnimation;

    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;

    public GameRenderer(GameWorld world, int bottom) {
        gameWorld = world;
        gameHeight = bottom;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, bottom);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
        setupTweens();
    }

    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }

    private void initGameObjects() {

        leftFrog = gameWorld.getLeftFrog();
        rightFrog = gameWorld.getRightFrog();
        handler = gameWorld.getHandler();
        leftElements = handler.getLeftElements();
        rightElements = handler.getRightElements();
    }

    private void initAssets() {
        // Animations
        leftFrogAnimation = AssetLoader.leftFrogAnimation;
        rightFrogAnimation = AssetLoader.rightFrogAnimation;
        leftCrocodileAnimation = AssetLoader.leftCrocodileAnimation;
        rightCrocodileAnimation = AssetLoader.rightCrocodileAnimation;
        leftFlyAnimation = AssetLoader.leftFlyAnimation;
        rightFlyAnimation = AssetLoader.rightFlyAnimation;
        background = AssetLoader.backgroundAnimation;

        leftFrogEaten = AssetLoader.leftFrogEaten;
        rightFrogEaten = AssetLoader.rightFrogEaten;

    }

    private void drawMenuUI() {
        batch.draw(AssetLoader.twoFrogsLogo, 136 / 2 - 56, (gameHeight/2) - 50,
                AssetLoader.twoFrogsLogo.getRegionWidth() / 1.2f,
                AssetLoader.twoFrogsLogo.getRegionHeight() / 1.2f);

        menuButtons.get(0).draw(batch);
    }

    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.disableBlending();
        batch.draw(background.getKeyFrame(runTime), 0, 0, 136, gameHeight);
        batch.enableBlending();

        if (gameWorld.isRunning()) {
            leftFrogRenderer(runTime);
            rightFrogRenderer(runTime);
            leftElementsRenderer(runTime);
            rightElementsRenderer(runTime);
            drawScore();

        } else if (gameWorld.isMenu()) {
            leftFrogRenderer(runTime);
            rightFrogRenderer(runTime);
            drawMenuUI();

        } else if (gameWorld.isGameOver() || gameWorld.isHighScore()) {

            leftElementsRenderer(runTime);
            rightElementsRenderer(runTime);

            if(leftFrog.wasEaten()) {
                rightFrogRenderer(runTime);
                deadLeftFrogRender(leftElements.first());
            } else if(rightFrog.wasEaten()) {
                leftFrogRenderer(runTime);
                deadRightFrogRender(rightElements.first());
            }

            if (gameWorld.isGameOver()) {
                AssetLoader.font.draw(batch, "Game Over", 32, 55);
                AssetLoader.font.draw(batch, "High Score:", 27, 70);

                String highScore = AssetLoader.getHighScore() + "";
                // Draw text
                AssetLoader.font.draw(batch, highScore, (136 / 2)
                        - (4 * highScore.length()), 85);
                leftFrogRenderer(runTime);
                rightFrogRenderer(runTime);
            }
            else {
                AssetLoader.font.draw(batch, "High Score!", 27, 55);
                drawScore();
                leftFrogRenderer(runTime);
                rightFrogRenderer(runTime);
            }

            menuButtons.get(1).draw(batch);
        }

        batch.end();
        drawTransition(delta);
    }

    private void drawScore() {
        int length = ("" + gameWorld.getScore()).length();
        AssetLoader.font.draw(batch, "" + gameWorld.getScore(),
                68 - (3 * length), (gameHeight/2) - 83);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 1, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }


    private void leftFrogRenderer(float runTime) {
        batch.draw(leftFrogAnimation.getKeyFrame(runTime), leftFrog.getX(),
                leftFrog.getY(), leftFrog.getWidth() / 2.0f, leftFrog.getHeight() / 2.0f,
                leftFrog.getWidth(), leftFrog.getHeight(), 1, 1, leftFrog.getRotation());
    }

    private void rightFrogRenderer(float runTime) {
        batch.draw(rightFrogAnimation.getKeyFrame(runTime), rightFrog.getX(), rightFrog.getY(),
                rightFrog.getWidth() / 2.0f, rightFrog.getHeight() / 2.0f,
                rightFrog.getWidth(), rightFrog.getHeight(), 1, 1, rightFrog.getRotation());
    }

    private void deadLeftFrogRender(Scrollable croc) {
        batch.draw(leftFrogEaten, croc.getX(), croc.getY(), croc.getWidth(), croc.getHeight());
    }

    private void deadRightFrogRender(Scrollable croc) {
        batch.draw(rightFrogEaten, croc.getX(), croc.getY(), croc.getWidth(), croc.getHeight());
    }

    private void leftElementsRenderer(float runTime) {

        for(int i = 0; i < leftElements.size; i++) {
            if(!leftElements.get(i).isStarted()){ break; }

            if(leftElements.get(i).getId() == 0) {
                leftAnimation = leftCrocodileAnimation;
            }
            else {
                leftAnimation = leftFlyAnimation;
            }
            batch.draw(leftAnimation.getKeyFrame(runTime), leftElements.get(i).getX(),
                    leftElements.get(i).getY(), leftElements.get(i).getWidth(), leftElements.get(i).getHeight());
        }
    }

    private void rightElementsRenderer(float runTime) {
        for(int i = 0; i < rightElements.size; i++) {
            if(!rightElements.get(i).isStarted()) { break; }

            if(rightElements.get(i).getId() == 0) {
                rightAnimation = rightCrocodileAnimation;
            }
            else {
                rightAnimation = rightFlyAnimation;
            }
            batch.draw(rightAnimation.getKeyFrame(runTime), rightElements.get(i).getX(),
                    rightElements.get(i).getY(), rightElements.get(i).getWidth(), rightElements.get(i).getHeight());
        }
    }
}
