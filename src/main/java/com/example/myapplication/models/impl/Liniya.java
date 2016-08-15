package com.example.myapplication.models.impl;

import android.util.Log;

import com.example.myapplication.constants.Constants;
import com.example.myapplication.models.Shape;

/**
 * Created by User on 10.07.2016.
 */
public class Liniya implements Shape {
    public static final String TAG = "myLogs";
    Constants con = new Constants();
    int step=0;
    int fRow = 0, fColumn=0;
    @Override
    public int create(int[][] pole) {
        if (pole[0][3] == 0 && pole[0][4] == 0 && pole[0][5] == 0 && pole[0][6] == 0) {
            pole[0][3] = con.LIN;
            pole[0][4] = con.LIN;
            pole[0][5] = con.LIN;
            pole[0][6] = con.LIN;
            return 0;
        } else return 8;
    }

    @Override
    public boolean down(int[][] pole, int currentState) {
        identFirstBlock(pole);
        if (step==0){step++; return true;}
        step++;
        boolean flag=false;
        //Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if (fRow == 19) {
                    return false;
                }
                if (pole[fRow + 1][fColumn] == 0 && pole[fRow + 1][fColumn + 1] == 0 && pole[fRow + 1][fColumn + 2] == 0 && pole[fRow + 1][fColumn + 3] == 0) {
                    pole[fRow + 1][fColumn] = con.LIN;
                    pole[fRow + 1][fColumn + 1] = con.LIN;
                    pole[fRow + 1][fColumn + 2] = con.LIN;
                    pole[fRow + 1][fColumn + 3] = con.LIN;
                    pole[fRow][fColumn] = 0;
                    pole[fRow][fColumn + 1] = 0;
                    pole[fRow][fColumn + 2] = 0;
                    pole[fRow][fColumn + 3] = 0;
                    flag = true;
                }
                break;
            case 1:
                if (fRow == 16) {
                    return false;
                }
                if (pole[fRow + 4][fColumn] == 0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 4][fColumn] = con.LIN;
                    flag = true;
                }
                break;
        }
        return flag;

    }
//TODO доделать метод
    @Override
    public boolean left(int[][] pole, int currentState) {
        identFirstBlock(pole);
        boolean flag=false;
        switch (currentState){
            case 0:
                if (fColumn==0){return false;}
                if (pole[fRow][fColumn-1]==0){
                    pole[fRow][fColumn-1]=con.LIN;
                    pole[fRow][fColumn+3]=0;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==0){return false;}
                if(pole[fColumn-1][fRow]==0 && pole[fColumn-1][fRow+1]==0 && pole[fColumn-1][fRow+2]==0 && pole[fColumn-1][fRow+3]==0){
                    pole[fColumn][fRow]=0;
                    pole[fColumn][fRow+1]=0;
                    pole[fColumn][fRow+2]=0;
                    pole[fColumn][fRow+3]=0;
                    pole[fColumn-1][fRow]=con.LIN;
                    pole[fColumn-1][fRow+1]=con.LIN;
                    pole[fColumn-1][fRow+2]=con.LIN;
                    pole[fColumn-1][fRow+3]=con.LIN;
                    flag = true;
                }
                break;
        }
        return flag;
    }

    @Override
    public boolean right(int[][] pole, int currentState) {
        identFirstBlock(pole);
        boolean flag=false;
        switch (currentState){
            case 0:
                if (fColumn==6){return false;}
                if (pole[fRow][fColumn+4]==0){
                    pole[fRow][fColumn]=0;
                    pole[fRow][fColumn+4]=con.LIN;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==9){return false;}
                if(pole[fColumn+1][fRow]==0 && pole[fColumn+1][fRow+1]==0 && pole[fColumn+1][fRow+2]==0 && pole[fColumn+1][fRow+3]==0){
                    pole[fColumn][fRow]=0;
                    pole[fColumn][fRow+1]=0;
                    pole[fColumn][fRow+2]=0;
                    pole[fColumn][fRow+3]=0;
                    pole[fColumn+1][fRow]=con.LIN;
                    pole[fColumn+1][fRow+1]=con.LIN;
                    pole[fColumn+1][fRow+2]=con.LIN;
                    pole[fColumn+1][fRow+3]=con.LIN;
                    flag = true;
                }
                break;
        }
        return flag;
    }

    @Override
    public int turnLeft(int[][] pole, int currentState) {
        identFirstBlock(pole);
        int flag=currentState;
        switch (currentState){
            case 0:
                if (pole[fRow-2][fColumn+1]==0 && pole[fRow-1][fColumn+1]==0 && pole[fRow-3][fColumn+1]==0){
                    pole[fRow-2][fColumn+1]=con.LIN;
                    pole[fRow-1][fColumn+1]=con.LIN;
                    pole[fRow-3][fColumn+1]=con.LIN;
                    pole[fRow][fColumn]=0;
                    pole[fRow][fColumn+2]=0;
                    pole[fRow][fColumn+3]=0;
                    flag = 1;
                }
                break;
            case 1:
                if (pole[fRow+3][fColumn-1]==0 && pole[fRow+3][fColumn+1]==0 && pole[fRow+3][fColumn+2]==0){
                    pole[fRow][fColumn]=0;
                    pole[fRow+1][fColumn]=0;
                    pole[fRow+2][fColumn]=0;
                    pole[fRow+3][fColumn-1]=con.LIN;
                    pole[fRow+3][fColumn+1]=con.LIN;
                    pole[fRow+3][fColumn+2]=con.LIN;
                    flag = 0;
                }
                break;
        }
        return flag;
    }

    @Override
    public int turnRight(int[][] pole, int currentState) {
        return turnLeft(pole, currentState);
    }

    @Override
    public void identFirstBlock(int[][] pole) {
        for (int i=con.ROW-1;i>=0;i--){
            for(int j=con.COLUMN-1;j>=0;j--){
                if(pole[i][j]==1){
                    fRow=i;
                    fColumn=j;
                }
            }
        }
    }
}
