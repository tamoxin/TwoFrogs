package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;
import com.tamoxin.gameobjects.Crocodile;
import com.tamoxin.gameobjects.Fly;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameobjects.ScrollHandler;
import com.tamoxin.gameobjects.Scrollable;
import com.tamoxin.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private Random column;

    private SpriteBatch batch;

    private int gameHeight;
    private int gameMid;

    // Game Objects
    private Frog leftFrog;
    private Frog rightFrog;
    private Queue <Crocodile> rightCrocodiles, leftCrocodiles;
    private Queue <Fly> leftFlies, rightFlies;
    private Queue <Scrollable> leftElements, rightElements;

    // Game Assets
    private TextureRegion background;
    private TextureRegion lFrog, lFrogStart, lFrogEnd;
    private TextureRegion rFrog, rFrogStart, rFrogEnd;
    private TextureRegion[] leftCrocodileTextureRegion, rightCrocodileTextureRegion;
    private TextureRegion[] leftFly, rightFly;
    private Animation leftFrogAnimation, rightFrogAnimation, rightCrocodileAnimation,
                        leftCrocodileAnimation, leftFlyAnimation, rightFlyAnimation;
    private Animation leftAnimation, rightAnimation;

    // Constructor
    public GameRenderer(GameWorld world, int bottom) {
        gameWorld = world;

        gameHeight = bottom;
        gameMid = gameHeight / 2;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, bottom);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();

        column =  new Random();
    }

    private void initGameObjects() {
        leftFrog = gameWorld.getLeftFrog();
        rightFrog = gameWorld.getRightFrog();
        leftFlies = gameWorld.getLeftFlies();
        rightFlies = gameWorld.getRightFlies();
        leftCrocodiles = gameWorld.getLeftCrocodiles();
        rightCrocodiles = gameWorld.getRightCrocodiles();
        // Queues of elements
        leftElements = gameWorld.getLeftElementsQueue();
        rightElements = gameWorld.getRightElementsQueue();
    }

    private void initAssets() {
        // Background
        background = AssetLoader.background;

        // Left Frog
        lFrog = AssetLoader.lFrog;
        lFrogStart = AssetLoader.lFrogStart;
        lFrogEnd = AssetLoader.lFrogEnd;

        // Right Frog
        rFrog = AssetLoader.rFrog;
        rFrogStart = AssetLoader.rFrogStart;
        rFrogEnd = AssetLoader.rFrogEnd;

        // Left Crocodile
        leftCrocodileTextureRegion = new TextureRegion[5];
        System.arraycopy(AssetLoader.leftCrocodile, 0, leftCrocodileTextureRegion, 0, leftCrocodileTextureRegion.length);
        leftCrocodileAnimation = AssetLoader.rightCrocodileAnimation;

        // Right Crocodile
        rightCrocodileTextureRegion = new TextureRegion[5];
        System.arraycopy(AssetLoader.rightCrocodile, 0, rightCrocodileTextureRegion, 0, rightCrocodileTextureRegion.length);
        rightCrocodileAnimation = AssetLoader.rightCrocodileAnimation;

        // Left Fly
        leftFly = new TextureRegion[4];
        System.arraycopy(AssetLoader.leftFly, 0, leftFly, 0, leftFly.length);
        leftFlyAnimation = AssetLoader.leftFlyAnimation;

        // Right Fly
        rightFly = new TextureRegion[4];
        System.arraycopy(AssetLoader.rightFly, 0, rightFly, 0, rightFly.length);
        rightFlyAnimation = AssetLoader.rightFlyAnimation;

        // Animations
        leftFrogAnimation = AssetLoader.leftFrogAnimation;
        rightFrogAnimation = AssetLoader.rightFrogAnimation;
        leftCrocodileAnimation = AssetLoader.leftCrocodileAnimation;
        rightCrocodileAnimation = AssetLoader.rightCrocodileAnimation;
        leftFlyAnimation = AssetLoader.leftFlyAnimation;
        rightFlyAnimation = AssetLoader.rightFlyAnimation;

    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();
        batch.draw(background, 0, 0, 136, gameHeight);

        batch.enableBlending();

        leftElementsRenderer(runTime);
        leftFrogRenderer(runTime);
        rightFrogRenderer(runTime);
        //batch.draw(leftCrocodileAnimation.getKeyFrame(runTime), leftCrocodileTextureRegion.getX(),
        //        leftCrocodileTextureRegion.getY(), leftCrocodileTextureRegion.getWidth(), leftCrocodileTextureRegion.getHeight());

        batch.end();
    }

    // Crocodile id = 0
    // Fly id = 1
    private Scrollable rightCollisionsRenderer() {

        int id = column.nextInt(2);
        Gdx.app.log("ID right",id + "");

        if(id == 0) {
            return leftCrocodiles.get(0);
        }

        return leftFlies.get(0);
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

    private void leftElementsRenderer(float runTime) {
        for(int i = 0; i < leftElements.size; i++) {
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

    }
}
