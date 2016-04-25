package com.tamoxin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Preferences prefs;

    // UI assets
    public static Texture logoTexture;
    public static TextureRegion logo;
    public static Texture playTexture;
    public static TextureRegion playButtonUp, playButtonDown;
    public static Texture restartTexture;
    public static TextureRegion restartButtonUp, restartButtonDown;
    public static Texture twoFrogsTexture;
    public static TextureRegion twoFrogsLogo;


    // Background assets
    public static Texture bg;
    public static TextureRegion[] background = new TextureRegion[5];
    public static Animation backgroundAnimation;

    // Left Frog assets
    public static Texture leftFrogTexture;
    public static Animation leftFrogAnimation;
    public static TextureRegion lFrog, lFrogStart, lFrogEnd,
            leftFrog[] = new TextureRegion[3];
    public static Texture leftFrogDead;
    public static TextureRegion leftFrogEaten;

    // Right Frog assets
    public static Texture rightFrogTexture;
    public static Animation rightFrogAnimation;
    public static TextureRegion rFrog, rFrogStart, rFrogEnd,
            rightFrog[] = new TextureRegion[3];
    public static Texture rightFrogDead;
    public static TextureRegion rightFrogEaten;

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

    // Sounds
    public static Sound dead;
    public static Sound eat;

    // Fonts
    public static BitmapFont font;

    public static void load() {

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        logo = new TextureRegion(logoTexture, 0, 0, 265, 355);

        playTexture = new Texture(Gdx.files.internal("data/playButton.png"));
        playTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        playButtonUp = new TextureRegion(playTexture, 0, 0, 32, 32);
        playButtonDown = new TextureRegion(playTexture, 0, 0, 32, 32);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        restartTexture = new Texture(Gdx.files.internal("data/restartButton.png"));
        restartTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        restartButtonUp = new TextureRegion(restartTexture);
        restartButtonDown = new TextureRegion(restartTexture);
        restartButtonUp.flip(false, true);
        restartButtonDown.flip(false, true);

        twoFrogsTexture = new Texture(Gdx.files.internal("data/2FrogsLogo.png"));
        twoFrogsTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        twoFrogsLogo = new TextureRegion(twoFrogsTexture, 0, 0, 124, 22);
        twoFrogsLogo.flip(false, true);

        loadBackground();
        loadFrogs();
        loadCrocodiles();
        loadFlies();

        leftFrogDead = new Texture(Gdx.files.internal("data/leftFrogDead.png"));
        leftFrogDead.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        leftFrogEaten = new TextureRegion(leftFrogDead);
        leftFrogEaten.flip(false, true);

        rightFrogDead = new Texture(Gdx.files.internal("data/rightFrogDead.png"));
        rightFrogDead.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rightFrogEaten = new TextureRegion(rightFrogDead);
        rightFrogEaten.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/sounds/dead.ogg"));
        eat = Gdx.audio.newSound(Gdx.files.internal("data/sounds/eat.ogg"));

        font = new BitmapFont(Gdx.files.internal("data/fonts/pressStart2P.fnt"));
        font.getData().setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("2 Frogs");

        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }


    }

    private static void loadBackground() {
        bg = new Texture(Gdx.files.internal("data/background.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        for(int i = 0, x = 0; i < background.length; i++, x+=50){
            background[i] = new TextureRegion(bg, x, 0, 48, 136);
        }
        backgroundAnimation = new Animation(0.5f, background);
        backgroundAnimation.setPlayMode(Animation.PlayMode.LOOP);
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

        for (TextureRegion aLeftFly : leftFly) aLeftFly.flip(false, true);

        leftFlyAnimation = new Animation(0.041f, leftFly);
        leftFlyAnimation.setPlayMode(Animation.PlayMode.LOOP);

        // Right Fly
        rightFlyTexture = new Texture(Gdx.files.internal("data/rightFly.png"));
        rightFlyTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        rFly1 = new TextureRegion(rightFlyTexture, 0, 0, 29, 24);
        rFly2 = new TextureRegion(rightFlyTexture, 29, 0, 29, 24);
        rFly3 = new TextureRegion(rightFlyTexture, 58, 0, 29, 24);
        rFly4 = new TextureRegion(rightFlyTexture, 87, 0, 29, 24);

        rightFly[0] = rFly1;
        rightFly[1] = rFly2;
        rightFly[2] = rFly3;
        rightFly[3] = rFly4;

        for (TextureRegion aRightFly : rightFly) aRightFly.flip(false, true);

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

        for (TextureRegion aLeftFrog : leftFrog) aLeftFrog.flip(false, true);

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

        for (TextureRegion aRightFrog : rightFrog) aRightFrog.flip(false, true);

        rightFrogAnimation = new Animation(0.15f, rightFrog);
        rightFrogAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        bg.dispose();
        leftFrogDead.dispose();
        rightFrogDead.dispose();
        leftFrogTexture.dispose();
        rightFrogTexture.dispose();
        leftFlyTexture.dispose();
        rightFlyTexture.dispose();
        leftCrocodileTexture.dispose();
        rightCrocodileTexture.dispose();
        logoTexture.dispose();
        playTexture.dispose();
        twoFrogsTexture.dispose();
        dead.dispose();
        eat.dispose();
        font.dispose();
    }
}
