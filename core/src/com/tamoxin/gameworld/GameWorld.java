package com.tamoxin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.tamoxin.gameobjects.Crocodile;
import com.tamoxin.gameobjects.Fly;
import com.tamoxin.gameobjects.Frog;
import com.tamoxin.gameobjects.Scrollable;

import java.util.Random;

/**
 * Created by Marco on 3/27/2016.
 */
public class GameWorld {

    private final int OBJECTS_SPEED = 100;
    private final int CROCODILE_HEIGHT = 29;
    private final int CROCODILE_WIDTH = 31;
    private final int FLY_HEIGHT = 12;//24
    private final int FLY_WIDTH = 14;//29
    private final int OBJECTS_GAP = (Gdx.graphics.getBackBufferHeight() / (Gdx.graphics.getWidth() / 136))/2;
    private final int SCROLL_SPEED = 100;

    private Random random;

    private Frog leftFrog, rightFrog;

    private Crocodile leftCroc1, leftCroc2, leftCroc3;
    private Crocodile rightCroc1, rightCroc2, rightCroc3;

    private Fly leftFly1, leftFly2, leftFly3;
    private Fly rightFly1, rightFly2, rightFly3;

    private Queue <Crocodile> leftCrocodilesQueue, rightCrocodilesQueue;
    private Queue <Fly> leftFliesQueue, rightFliesQueue;
    private Queue <Scrollable> leftElementsQueue, rightElementsQueue;

    public GameWorld(int bottom) {
        random = new Random();
        leftFrog = new Frog(2, bottom - 28, 26, 23);
        rightFrog = new Frog(73, bottom - 28, 26, 23);
        leftCrocodilesQueue = new Queue<Crocodile>();
        rightCrocodilesQueue = new Queue<Crocodile>();
        rightFliesQueue = new Queue<Fly>();
        leftFliesQueue = new Queue<Fly>();
        leftElementsQueue = new Queue<Scrollable>();
        rightElementsQueue = new Queue<Scrollable>();

        // Fill the queues
        setRightCrocodilesQueue();
        setLeftCrocodilesQueue();
        setLeftFliesQueue();
        setRightFliesQueue();

        // Fill the elements queues
        for(int i = 0; i < 3; i++) {
            addRandomToLeftElementsQueue();
            addRandomToRightElementsQueue();
        }
        leftElementsQueue.first().start();
        rightElementsQueue.first().start();
    }

    public void update(float delta) {

        leftFrog.update(delta);
        rightFrog.update(delta);
        updateLeftElementsQueue(delta);
        updateRightElementsQueue(delta);

    }

    // Update queues
    private void updateLeftElementsQueue(float delta) {
        for(int i = 0; i < leftElementsQueue.size; i++) {

            leftElementsQueue.get(i).update(delta);

            if(leftElementsQueue.get(i).hasPassedMidPoint()) {
                leftElementsQueue.get(i + 1).start();
            }

            if(leftElementsQueue.get(i).isScrolledDown()) {
                leftElementsQueue.get(i).reset(0);
                if(leftElementsQueue.get(i).getId() == 0) {
                    leftCrocodilesQueue.addLast((Crocodile) leftElementsQueue.get(i));
                }
                else {
                    leftFliesQueue.addLast((Fly) leftElementsQueue.get(i));
                }

                leftElementsQueue.removeIndex(i);
                addRandomToLeftElementsQueue();
            }
        }
    }

    private void updateRightElementsQueue(float delta) {
        for(int j = 0; j < rightElementsQueue.size; j++) {
            rightElementsQueue.get(j).update(delta);

            if(rightElementsQueue.get(j).hasPassedMidPoint()) {
                rightElementsQueue.get(j + 1).start();
            }

            if(rightElementsQueue.get(j).isScrolledDown()) {
                rightElementsQueue.get(j).reset(0);
                if(rightElementsQueue.get(j).getId() == 0) {
                    rightCrocodilesQueue.addLast((Crocodile) rightElementsQueue.get(j));
                } else {
                    rightFliesQueue.addLast((Fly) rightElementsQueue.get(j));
                }

                rightElementsQueue.removeIndex(j);
                addRandomToRightElementsQueue();
            }
        }
    }

    // Setters
    private void setRightCrocodilesQueue() {
        // Crocodiles should be 31x29
        rightCroc1 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc2 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc3 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        rightCrocodilesQueue.addFirst(rightCroc1);
        rightCrocodilesQueue.addFirst(rightCroc2);
        rightCrocodilesQueue.addFirst(rightCroc3);
    }

    private void setRightFliesQueue() {
        rightFly1 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly2 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly3 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        rightFliesQueue.addFirst(rightFly1);
        rightFliesQueue.addFirst(rightFly2);
        rightFliesQueue.addFirst(rightFly3);
    }

    private void setLeftCrocodilesQueue() {
        leftCroc1 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc2 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc3 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        leftCrocodilesQueue.addFirst(leftCroc1);
        leftCrocodilesQueue.addFirst(leftCroc2);
        leftCrocodilesQueue.addFirst(leftCroc3);
    }

    private void setLeftFliesQueue() {
        leftFly1 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly2 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly3 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        leftFliesQueue.addFirst(leftFly1);
        leftFliesQueue.addFirst(leftFly2);
        leftFliesQueue.addFirst(leftFly3);
    }

    // Add elements at random position
    public void addRandomToLeftElementsQueue() {
        int type = random.nextInt(2);
        if(type == 0){
            leftElementsQueue.addLast(leftCrocodilesQueue.removeFirst());
        } else {
            leftElementsQueue.addLast(leftFliesQueue.removeFirst());
        }
        leftElementsQueue.last().reset(0);
    }

    public void addRandomToRightElementsQueue() {
        int type = random.nextInt(2);
        if(type == 0) {
            rightElementsQueue.addLast(rightCrocodilesQueue.removeFirst());
        } else {
            rightElementsQueue.addLast(rightFliesQueue.removeFirst());
        }
        rightElementsQueue.last().reset(0);
    }

    // Getters
    public Queue<Fly> getLeftFlies() {
        return leftFliesQueue;
    }

    public Queue<Fly> getRightFlies() {
        return rightFliesQueue;
    }

    public Queue<Crocodile> getLeftCrocodiles() {
        return leftCrocodilesQueue;
    }

    public Queue<Crocodile> getRightCrocodiles() {
        return rightCrocodilesQueue;
    }

    public Queue<Scrollable> getLeftElementsQueue() {
        return leftElementsQueue;
    }

    public Queue<Scrollable> getRightElementsQueue() {
        return rightElementsQueue;
    }

    public Frog getLeftFrog() {
        return leftFrog;
    }

    public Frog getRightFrog() {
        return rightFrog;
    }

}
