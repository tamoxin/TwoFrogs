package com.tamoxin.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Queue;

import java.util.Random;

/**
 * Created by Marco on 4/16/2016.
 */
public class ScrollHandler {

    private final int OBJECTS_SPEED = 130;
    private final int CROCODILE_HEIGHT = 29;
    private final int CROCODILE_WIDTH = 31;
    private final int FLY_HEIGHT = 12;//24
    private final int FLY_WIDTH = 14;//29

    private Crocodile leftCroc1, leftCroc2, leftCroc3;
    private Crocodile rightCroc1, rightCroc2, rightCroc3;

    private Fly leftFly1, leftFly2, leftFly3;
    private Fly rightFly1, rightFly2, rightFly3;

    private Queue<Crocodile> leftCrocodiles, rightCrocodiles;
    private Queue <Fly> leftFlies, rightFlies;
    private Queue <Scrollable> leftElements, rightElements;

    private Random random;

    public ScrollHandler() {
        random = new Random();

        leftCrocodiles = new Queue<Crocodile>();
        rightCrocodiles = new Queue<Crocodile>();
        rightFlies = new Queue<Fly>();
        leftFlies = new Queue<Fly>();
        leftElements = new Queue<Scrollable>();
        rightElements = new Queue<Scrollable>();

        // Fill the queues
        setRightCrocodilesQueue();
        setLeftCrocodilesQueue();
        setLeftFliesQueue();
        setRightFliesQueue();
        for(int i = 0; i < 3; i++) {
            addRandomToLeftElementsQueue();
            addRandomToRightElementsQueue();
        }

        // starts the first element of each queue
        leftElements.first().start();
        rightElements.first().start();
    }

    public void update(float delta) {
        updateLeftElementsQueue(delta);
        updateRightElementsQueue(delta);
    }

    public void stop() {
        for(int i = 0; i < leftElements.size; i++) {
            leftElements.get(i).stop();
            rightElements.get(i).stop();
        }
    }

    public boolean collides(Frog leftFrog, Frog rightFrog){
        for(int i = 0; i < leftElements.size; i++) {
            if(leftElements.get(i).collides(leftFrog) || rightElements.get(i).collides(rightFrog))
                return true;
        }
        return false;
    }

    public boolean collidesWithFly(Frog leftFrog, Frog rightFrog) {

        for(int i = 0; i < leftElements.size; i++) {
            if(leftElements.get(i).collides(leftFrog) && leftElements.get(i).getId() == 1) {
                leftElements.get(i).scrollDown();
                return true;
            } else if(rightElements.get(i).collides(rightFrog) && rightElements.get(i).getId() == 1) {
                rightElements.get(i).scrollDown();
                return true;
            }
        }

        return false;
    }

    public boolean collidesWithCrocodile(Frog leftFrog, Frog rightFrog) {
        for(int i = 0; i < leftElements.size; i++) {

            if(leftElements.get(i).collides(leftFrog) && leftElements.get(i).getId() == 0) {
                leftFrog.setX(leftElements.get(i).getX() + 2);
                leftElements.get(i).setYPosition(leftFrog.getY() - 4);
                leftElements.get(i).setEaten(true);
                leftFrog.setEatenState(true);
                leftFrog.killFrog();
                rightFrog.killFrog();
                return true;

            } else if(rightElements.get(i).collides(rightFrog) && rightElements.get(i).getId() == 0 ) {
                rightFrog.setX(rightElements.get(i).getX() + 2);
                rightElements.get(i).setYPosition(rightFrog.getY() - 4);
                rightElements.get(i).setEaten(true);
                rightFrog.setEatenState(true);
                rightFrog.killFrog();
                leftFrog.killFrog();
                return true;
            }
        }
        return false;
    }

    public boolean missFly(Rectangle ground) {
        for(int i = 0; i < leftElements.size; i++) {
            if(leftElements.get(i).collidesGround(ground) && leftElements.get(i).getId() == 1) {
                return true;
            } else if(rightElements.get(i).collidesGround(ground) && rightElements.get(i).getId() == 1) {
                return true;
            }
        }
        return false;
    }

    // Update queues
    private void updateLeftElementsQueue(float delta) {
        for(int i = 0; i < leftElements.size; i++) {
            leftElements.get(i).update(delta);

            if(leftElements.get(i).hasPassedMidPoint()) {
                leftElements.get(i + 1).start();
            }

            // TODO: 4/17/2016 Check this method when implementing game over
            if(leftElements.get(i).isScrolledDown()) {
                leftElements.get(i).reset(0);
                if(leftElements.get(i).getId() == 0) {
                    leftCrocodiles.addLast((Crocodile) leftElements.get(i));
                } else {
                    leftFlies.addLast((Fly) leftElements.get(i));
                }
                leftElements.removeIndex(i);
                addRandomToLeftElementsQueue();
            }
        }
    }

    private void updateRightElementsQueue(float delta) {
        for(int j = 0; j < rightElements.size; j++) {
            rightElements.get(j).update(delta);

            if(rightElements.get(j).hasPassedMidPoint()) {
                rightElements.get(j + 1).start();
            }

            if(rightElements.get(j).isScrolledDown()) {
                rightElements.get(j).reset(0);
                if(rightElements.get(j).getId() == 0) {
                    rightCrocodiles.addLast((Crocodile) rightElements.get(j));
                } else {
                    rightFlies.addLast((Fly) rightElements.get(j));
                }

                rightElements.removeIndex(j);
                addRandomToRightElementsQueue();
            }
        }
    }

    // Add elements at random position
    public void addRandomToLeftElementsQueue() {
        int type = random.nextInt(10);
        if(type < 5){
            leftElements.addLast(leftCrocodiles.removeFirst());
        } else {
            leftElements.addLast(leftFlies.removeFirst());
        }
        leftElements.last().reset(0);
    }

    public void addRandomToRightElementsQueue() {
        int type = random.nextInt(10);
        if(type < 5) {
            rightElements.addLast(rightCrocodiles.removeFirst());
        } else {
            rightElements.addLast(rightFlies.removeFirst());
        }
        rightElements.last().reset(0);
    }

    // Setters
    private void setRightCrocodilesQueue() {
        // Crocodiles should be 31x29
        rightCroc1 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc2 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        rightCroc3 = new Crocodile(71, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        rightCrocodiles.addFirst(rightCroc1);
        rightCrocodiles.addFirst(rightCroc2);
        rightCrocodiles.addFirst(rightCroc3);
    }

    private void setRightFliesQueue() {
        rightFly1 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly2 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        rightFly3 = new Fly(80, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        rightFlies.addFirst(rightFly1);
        rightFlies.addFirst(rightFly2);
        rightFlies.addFirst(rightFly3);
    }

    private void setLeftCrocodilesQueue() {
        leftCroc1 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc2 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);
        leftCroc3 = new Crocodile(0, 0, CROCODILE_WIDTH, CROCODILE_HEIGHT, OBJECTS_SPEED);

        leftCrocodiles.addFirst(leftCroc1);
        leftCrocodiles.addFirst(leftCroc2);
        leftCrocodiles.addFirst(leftCroc3);
    }

    private void setLeftFliesQueue() {
        leftFly1 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly2 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);
        leftFly3 = new Fly(10, 0, FLY_WIDTH, FLY_HEIGHT, OBJECTS_SPEED);

        leftFlies.addFirst(leftFly1);
        leftFlies.addFirst(leftFly2);
        leftFlies.addFirst(leftFly3);
    }

    // Getters
    public Queue<Fly> getLeftFlies() {
        return leftFlies;
    }

    public Queue<Fly> getRightFlies() {
        return rightFlies;
    }

    public Queue<Crocodile> getLeftCrocodiles() {
        return leftCrocodiles;
    }

    public Queue<Crocodile> getRightCrocodiles() {
        return rightCrocodiles;
    }

    public Queue<Scrollable> getLeftElements() {
        return leftElements;
    }

    public Queue<Scrollable> getRightElements() {
        return rightElements;
    }

    public void onRestart() {
        leftElements.addLast(leftElements.removeFirst());
        rightElements.addLast(rightElements.removeFirst());
        for(int i = 0; i < leftElements.size; i++){
            leftElements.get(i).onRestart();
            rightElements.get(i).onRestart();
            leftElements.first().start();
            rightElements.first().start();
        }
    }
}
