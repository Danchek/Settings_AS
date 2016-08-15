package com.example.myapplication.models.impl;

import com.example.myapplication.models.Shape;

/**
 * Created by User on 10.07.2016.
 */
public class ObtatnayaL implements Shape {
    @Override
    public int create(int[][] pole) {
        return 0;
    }


    @Override
    public boolean left(int[][] pole, int currentState) {
        return false;
    }

    @Override
    public boolean right(int[][] pole, int currentState) {
        return false;
    }

    @Override
    public boolean down(int[][] pole, int currentState) {
        return false;
    }

    @Override
    public int turnLeft(int[][] pole, int currentState) {
        return 0;
    }

    @Override
    public int turnRight(int[][] pole, int currentState) {
        return 0;
    }

    @Override
    public void identFirstBlock(int[][] pole) {

    }
}
