package com.tamoxin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;

import java.util.Random;

/**
 * Created by Marco on 3/29/2016.
 */
public class ScrollHandler {


    private static final int OBJECTS_GAP = (Gdx.graphics.getBackBufferHeight() / (Gdx.graphics.getWidth() / 136))/2;
    private static final int SCROLL_SPEED = 100;

    private Queue <Crocodile> rightCrocodiles, leftCrocodiles;
    private Queue <Fly> leftFlies, rightFlies;
    private Queue <Scrollable> leftElements, rightElements;
    private Random random;

    public ScrollHandler(Queue<Crocodile> leftCrocodiles, Queue<Crocodile> rightCrocodiles, Queue<Fly> leftFlies, Queue<Fly> rightFlies,
                         Queue<Scrollable> leftElements, Queue<Scrollable> rightElements){

        random = new Random();
        this.leftCrocodiles = leftCrocodiles;
        this.rightCrocodiles = rightCrocodiles;
        this.leftFlies = leftFlies;
        this.rightFlies = rightFlies;
        this.leftElements = leftElements;
        this.rightElements = rightElements;
        setLeftElements();
        setRightElements();
    }

    public void update(float delta) {
        for(int i = 0; i < leftElements.size; i++) {
            leftElements.get(i).update(delta);
            if(leftElements.get(i).isScrolledDown()) {
                leftElements.get(i).reset(leftElements.last().getTailY());
                if(leftElements.get(i).getId() == 0) {
                    leftCrocodiles.addLast((Crocodile) leftElements.get(i));
                }
                else {
                    leftFlies.addLast((Fly) leftElements.get(i));
                }

                leftElements.removeIndex(i);
                setLeftElements();
            }
        }

        for(int i = 0; i < rightElements.size; i++) {
            rightElements.get(i).update(delta);
            if(rightElements.get(i).isScrolledDown()) {
                rightElements.get(i).reset(rightElements.last().getTailY());
                if(rightElements.get(i).getId() == 0) {
                    rightCrocodiles.addLast((Crocodile) rightElements.get(i));
                }
                else {
                    rightFlies.addLast((Fly) rightElements.get(i));
                }

                rightElements.removeIndex(i);
                setRightElements();
            }
        }


    }

    private void setLeftElements() {
        int type;

        // This for fills the left elements queue
        for(int i = 0; i < leftElements.size; i++) {
            if(leftElements.get(i) == null || leftElements.size < 3) {
                type  = random.nextInt(2);
                if (type == 0) {
                    leftElements.addLast(leftCrocodiles.removeFirst());
                }
                else {
                    leftElements.addLast(leftFlies.removeFirst());
                }

                // Set the properties for the left element in the i position
                if(leftElements.size > 1) {
                    leftElements.get(i).setYPosition(OBJECTS_GAP - leftElements.get(i-1).getTailY());
                    leftElements.get(i).setVelocityY(SCROLL_SPEED);
                    leftElements.get(i).setRandomSide();
                }
            }
        }
    }

    private void setRightElements() {
        int type;

        // This for fills the right elements queue
        for(int i = 0; i < rightElements.size; i++) {
            if(rightElements.get(i) == null) {
                type = random.nextInt(2);
                if (type == 0) {
                    rightElements.addLast(rightCrocodiles.removeFirst());
                } else {
                    rightElements.addLast(rightFlies.removeFirst());
                }

                // Set the properties for the right element in the i position
                if(rightElements.size > 1) {
                    rightElements.get(i).setYPosition(OBJECTS_GAP - rightElements.get(i-1).getTailY());
                    rightElements.get(i).setVelocityY(SCROLL_SPEED);
                    rightElements.get(i).setRandomSide();
                }
            }
        }
    }

    public Queue<Scrollable> getRightElements() {
        return rightElements;
    }

    public Queue<Scrollable> getLeftElements() {
        return  leftElements;
    }
}
