package com.example.myapplication.models;

/**
 * Created by User on 10.07.2016.
 */
public interface Shape {
    public int create(int [][]pole);
    public boolean down (int[][]pole, int currentState);
    public int turnLeft(int[][]pole, int currentState);
    public int turnRight(int[][]pole, int currentState);
    public boolean left (int[][]pole, int currentState);
    public boolean right (int[][]pole, int currentState);
    public void identFirstBlock (int[][]pole);

}
