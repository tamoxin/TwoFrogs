package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.helpers.AssetLoader;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private int gameHeight;
    private int bottom;

    // Game Objects
    Frog leftFrog;
    Frog rightFrog;

    // Game Assets
    private TextureRegion background;
    private TextureRegion lFrog, lFrogStart, lFrogEnd;
    private TextureRegion rFrog, rFrogStart, rFrogEnd;
    private Animation leftFrogAnimation, rightFrogAnimation;

    public GameRenderer(GameWorld world, int bottom) {
        gameWorld = world;

        this.gameHeight = gameHeight;
        this.bottom = bottom;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, bottom);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        leftFrog = gameWorld.getLeftFrog();
        rightFrog = gameWorld.getRightFrog();
    }

    private void initAssets() {
        background = AssetLoader.background;
        lFrog = AssetLoader.lFrog;
        lFrogStart = AssetLoader.lFrogStart;
        lFrogEnd = AssetLoader.lFrogEnd;
        rFrog = AssetLoader.rFrog;
        rFrogStart = AssetLoader.rFrogStart;
        rFrogEnd = AssetLoader.rFrogEnd;
        leftFrogAnimation = AssetLoader.leftFrogAnimation;
        rightFrogAnimation = AssetLoader.rightFrogAnimation;
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();
        batch.draw(background, 0, 0, 136, bottom);

        batch.enableBlending();

        batch.draw(leftFrogAnimation.getKeyFrame(runTime), leftFrog.getX(),
                leftFrog.getY(), leftFrog.getWidth() / 2.0f, leftFrog.getHeight() / 2.0f,
                leftFrog.getWidth(), leftFrog.getHeight(), 1, 1, leftFrog.getRotation());

        batch.draw(rightFrogAnimation.getKeyFrame(runTime), rightFrog.getX(), rightFrog.getY(),
                rightFrog.getWidth() / 2.0f, rightFrog.getHeight() / 2.0f,
                rightFrog.getWidth(), rightFrog.getHeight(), 1, 1, rightFrog.getRotation());

        batch.end();
    }
}
