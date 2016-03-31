package com.tamoxin.gameworld;

import com.badlogic.gdx.utils.Queue;
import com.tamoxin.gameobjects.Crocodile;
import com.tamoxin.gameobjects.Fly;
import com.tamoxin.gameobjects.Frog;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameWorld {

    private final float OBJECTS_SPEED = 150;
    private final int   CROCODILE_HEIGHT = 29;
    private final int   CROCODILE_WIDTH = 31;
    private final int   FLY_HEIGHT = 24;
    private final int   FLY_WIDTH = 29;

    private Frog leftFrog, rightFrog;

    private Crocodile leftCroc1, leftCroc2, leftCroc3;
    private Crocodile rightCroc1, rightCroc2, rightCroc3;

    private Fly leftFly1, leftFly2, leftFly3;
    private Fly rightFly1, rightFly2, rightFly3;

    private Queue <Crocodile> leftCrocodilesQueue, rightCrocodilesQueue;
    private Queue <Fly> leftFliesQueue, rightFliesQueue;

    public GameWorld(int bottom) {
        leftFrog = new Frog(2, bottom - 28, 26, 23);
        rightFrog = new Frog(73, bottom - 28, 26, 23);
        leftCrocodilesQueue = new Queue<Crocodile>();
        rightCrocodilesQueue = new Queue<Crocodile>();
        rightFliesQueue = new Queue<Fly>();
        leftFliesQueue = new Queue<Fly>();

        // Fill the queues
        setRightCrocodilesQueue();
        setLeftCrocodilesQueue();
        setLeftFliesQueue();
        setRightFliesQueue();
    }

    public void update(float delta) {
        leftFrog.update(delta);
        rightFrog.update(delta);
    }

    private void setRightCrocodilesQueue() {
        // Crocodiles should be 31x29
        rightCroc1 = new Crocodile(71, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc2 = new Crocodile(71, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc3 = new Crocodile(71, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        rightCrocodilesQueue.addFirst(rightCroc1);
        rightCrocodilesQueue.addFirst(rightCroc2);
        rightCrocodilesQueue.addFirst(rightCroc3);
    }

    private void setRightFliesQueue() {
        rightFly1 = new Fly(71, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly2 = new Fly(71, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly3 = new Fly(71, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        rightFliesQueue.addFirst(rightFly1);
        rightFliesQueue.addFirst(rightFly2);
        rightFliesQueue.addFirst(rightFly3);
    }

    private void setLeftCrocodilesQueue() {
        leftCroc1 = new Crocodile(2, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc2 = new Crocodile(2, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc3 = new Crocodile(2, -35, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        leftCrocodilesQueue.addFirst(leftCroc1);
        leftCrocodilesQueue.addFirst(leftCroc2);
        leftCrocodilesQueue.addFirst(leftCroc3);
    }

    // Note, the origin of the flies may need to be changed
    private void setLeftFliesQueue() {
        leftFly1 = new Fly(2, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly2 = new Fly(2, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly3 = new Fly(2, 35, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        leftFliesQueue.addFirst(leftFly1);
        leftFliesQueue.addFirst(leftFly2);
        leftFliesQueue.addFirst(leftFly3);
    }

    public Frog getLeftFrog() {
        return leftFrog;
    }

    public Frog getRightFrog() {
        return rightFrog;
    }

    public Queue<Fly> getLeftFlies() {
        return leftFliesQueue;
    }

    public Queue<Fly> getRightFlies() {
        return rightFliesQueue;
    }

    public Queue<Crocodile> getLeftCrocodile() {
        return leftCrocodilesQueue;
    }

    public Queue<Crocodile> getRightCrocodile() {
        return rightCrocodilesQueue;
    }
}
