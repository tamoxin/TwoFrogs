package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Marco on 3/27/2016.
 */
public class AssetLoader {

    // Background assets
    public static Texture bg;
    public static TextureRegion background;

    // Left Frog assets
    public static Texture leftFrogTexture;
    public static Animation leftFrogAnimation;
    public static TextureRegion lFrog, lFrogStart, lFrogEnd,
            leftFrog[] = new TextureRegion[3];

    // Right Frog assets
    public static Texture rightFrogTexture;
    public static Animation rightFrogAnimation;
    public static TextureRegion rFrog, rFrogStart, rFrogEnd,
            rightFrog[] = new TextureRegion[3];

    // Left Crocodile assets
    public static Texture leftCrocodileTexture;
    public static Animation leftCrocodileAnimation;
    public static TextureRegion[] leftCrocodile = new TextureRegion[5];

    // Right Crocodile assets
    public static Texture rightCrocodileTexture;
    public static Animation rightCrocodileAnimation;
    public static TextureRegion[] rightCrocodile = new TextureRegion[5];

    // Left Fly assets
    public static Texture leftFlyTexture;
    public static Animation leftFlyAnimation;
    public static TextureRegion lFly1, lFly2, lFly3, lFly4,
            leftFly[] = new TextureRegion[4];

    // Right Fly assets
    public static Texture rightFlyTexture;
    public static Animation rightFlyAnimation;
    public static TextureRegion rFly1, rFly2, rFly3, rFly4,
            rightFly[] = new TextureRegion[4];

    public static void load() {

        // Background
        bg = new Texture(Gdx.files.internal("data/background.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        background = new TextureRegion(bg);

        loadFrogs();
        loadCrocodiles();
        loadFlies();

    }

    private static void loadFlies() {
        // Left Fly
        leftFlyTexture = new Texture(Gdx.files.internal("data/leftFly.png"));
        leftFlyTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        lFly1 = new TextureRegion(leftFlyTexture, 0, 0, 29, 24);
        lFly2 = new TextureRegion(leftFlyTexture, 29, 0, 29, 24);
        lFly3 = new TextureRegion(leftFlyTexture, 58, 0, 29, 24);
        lFly4 = new TextureRegion(leftFlyTexture, 87, 0, 29, 24);

        leftFly[0] = lFly1;
        leftFly[1] = lFly2;
        leftFly[2] = lFly3;
        leftFly[3] = lFly4;

        for(int i = 0; i < leftFly.length; i++){
            leftFly[i].flip(false, true);
        }

        leftFlyAnimation = new Animation(0.041f, leftFly);
        leftFlyAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Fly
        rightFlyTexture = new Texture(Gdx.files.internal("data/rightFly.png"));
        rightFlyTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        rFly1 = new TextureRegion(leftFlyTexture, 0, 0, 29, 24);
        rFly2 = new TextureRegion(leftFlyTexture, 29, 0, 29, 24);
        rFly3 = new TextureRegion(leftFlyTexture, 58, 0, 29, 24);
        rFly4 = new TextureRegion(leftFlyTexture, 87, 0, 29, 24);

        rightFly[0] = rFly1;
        rightFly[1] = rFly2;
        rightFly[2] = rFly3;
        rightFly[3] = rFly4;

        for(int i = 0; i < rightFly.length; i++) {
            rightFly[i].flip(false, true);
        }

        rightFlyAnimation = new Animation(0.041f, rightFly);
        rightFlyAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private static void loadCrocodiles() {
        // Left Crocodile
        leftCrocodileTexture = new Texture(Gdx.files.internal("data/leftCroc.png"));
        leftCrocodileTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        for(int i = 0, x = 0; i < leftCrocodile.length; i++, x+=32) {
            leftCrocodile[i] = new TextureRegion(leftCrocodileTexture, x, 0, 32, 35);
            leftCrocodile[i].flip(false, true);
        }

        leftCrocodileAnimation = new Animation(0.10f, leftCrocodile);
        leftCrocodileAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Crocodile
        rightCrocodileTexture = new Texture(Gdx.files.internal("data/rightCroc.png"));
        rightCrocodileTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        for(int i = 0, x = 0; i < rightCrocodile.length; i++, x+=32) {
            rightCrocodile[i] = new TextureRegion(rightCrocodileTexture, x, 0, 32, 35);
            rightCrocodile[i].flip(false, true);
        }

        rightCrocodileAnimation = new Animation(0.10f, rightCrocodile);
        rightCrocodileAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    private static void loadFrogs() {
        // Left Frog
        leftFrogTexture = new Texture(Gdx.files.internal("data/leftFrog.png"));
        leftFrogTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        lFrog = new TextureRegion(leftFrogTexture, 0, 0, 22, 23);
        lFrogStart = new TextureRegion(leftFrogTexture, 23, 0, 26, 23);
        lFrogEnd = new TextureRegion(leftFrogTexture, 50, 0, 26, 23);

        leftFrog[0] = lFrog;
        leftFrog[1] = lFrogStart;
        leftFrog[2] = lFrogEnd;

        for(int i = 0; i < leftFrog.length; i++) {
            leftFrog[i].flip(false, true);
        }

        leftFrogAnimation = new Animation(0.15f, leftFrog);
        leftFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Frog
        rightFrogTexture = new Texture(Gdx.files.internal("data/rightFrog.png"));
        rightFrogTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        rFrog = new TextureRegion(rightFrogTexture, 0, 0, 22, 23);
        rFrogStart = new TextureRegion(rightFrogTexture, 23, 0, 26, 23);
        rFrogEnd = new TextureRegion(rightFrogTexture, 50, 0, 26, 23);

        rightFrog[0] = rFrog;
        rightFrog[1] = rFrogStart;
        rightFrog[2] = rFrogEnd;

        for(int i = 0; i < rightFrog.length; i++) {
            rightFrog[i].flip(false, true);
        }

        rightFrogAnimation = new Animation(0.15f, rightFrog);
        rightFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dispose() {
        bg.dispose();
        leftFrogTexture.dispose();
        rightFrogTexture.dispose();
        leftFlyTexture.dispose();
        rightFlyTexture.dispose();
        leftCrocodileTexture.dispose();
        rightCrocodileTexture.dispose();
    }
}
