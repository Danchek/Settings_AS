package com.example.myapplication.models.impl;

import android.util.Log;

import com.example.myapplication.constants.Constants;
import com.example.myapplication.models.Shape;

/**
 * Created by User on 10.07.2016.
 */
public class Dva implements Shape {
    public static final String TAG = "myLogs";
    Constants con = new Constants();
    int vid=con.DVA;
    int step=0;
    int fRow = 0, fColumn=0;
    @Override
    public int create(int[][] pole) {
        if (pole[0][3] == 0 && pole[0][4] == 0 && pole[1][5] == 0 && pole[1][4] == 0) {
            pole[0][3] = vid;
            pole[0][4] = vid;
            pole[1][4] = vid;
            pole[1][5] = vid;
            return 0;
        } else return 8;
    }

    @Override
    public boolean down(int[][] pole, int currentState) {
        //Log.d(TAG, "step = "+step);
        if (step==0){step++; return true;}
        boolean flag = false;
        identFirstBlock(pole);
        switch (currentState){
            case 0:
                if (fColumn==18) {return false;}
                if(pole[fRow+1][fColumn]==0 && pole[fRow+2][fColumn+1]==0 && pole[fRow+2][fColumn+2]==0){
                    pole[fRow][fColumn]=0;
                    pole[fRow][fColumn+1]=0;
                    pole[fRow+1][fColumn+2]=0;
                    pole[fRow+1][fColumn]=vid;
                    pole[fRow+2][fColumn+1]=vid;
                    pole[fRow+2][fColumn+2]=vid;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==17) {return false;}
                if(pole[fRow+3][fColumn-1]==0 && pole[fRow+2][fColumn]==0){
                    pole[fRow][fColumn]=0;
                    pole[fRow+1][fColumn-1]=0;
                    pole[fRow+3][fColumn-1]=vid;
                    pole[fRow+2][fColumn]=vid;
                    flag=true;
                }
                break;
        }
        return flag;
    }

    @Override
    public boolean left(int[][] pole, int currentState) {
        boolean flag = false;
        identFirstBlock(pole);
        switch (currentState){
            case 0:
                if (fColumn==0) {return false;}
                if(pole[fRow][fColumn-1]==0 && pole[fRow+1][fColumn]==0){
                    pole[fRow][fColumn+1]=0;
                    pole[fRow+1][fColumn+2]=0;
                    pole[fRow][fColumn-1]=vid;
                    pole[fRow+1][fColumn]=vid;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==1) {return false;}
                if(pole[fRow][fColumn-1]==0 && pole[fRow+1][fColumn-2]==0 && pole[fRow+2][fColumn-2]==0){
                    pole[fRow][fColumn]=0;
                    pole[fRow+1][fColumn]=0;
                    pole[fRow+2][fColumn-1]=0;
                    pole[fRow][fColumn-1]=vid;
                    pole[fRow+1][fColumn-2]=vid;
                    pole[fRow+2][fColumn-2]=vid;
                    flag=true;
                }
                break;
        }
        return flag;
    }

    @Override
    public boolean right(int[][] pole, int currentState) {
        boolean flag = false;
        identFirstBlock(pole);
        switch (currentState) {
            case 0:
                if (fColumn == 7) {
                    return false;
                }
                if (pole[fRow][fColumn+2] == 0 && pole[fRow + 1][fColumn+3] == 0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn + 1] = 0;
                    pole[fRow][fColumn + 2] = vid;
                    pole[fRow + 1][fColumn + 3] = vid;
                    flag = true;
                }
                break;
            case 1:
                if (fColumn == 9) {
                    return false;
                }
                if (pole[fRow][fColumn + 1] == 0 && pole[fRow + 1][fColumn + 1] == 0 && pole[fRow + 2][fColumn] == 0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn - 1] = 0;
                    pole[fRow + 2][fColumn - 1] = 0;
                    pole[fRow][fColumn + 1] = vid;
                    pole[fRow + 1][fColumn + 1] = vid;
                    pole[fRow + 2][fColumn] = vid;
                    flag = true;
                }
                break;
        }
        return flag;
    }

    @Override
    public int turnLeft(int[][] pole, int currentState) {
        int flag = currentState;
        identFirstBlock(pole);
        switch (currentState) {
            case 0:
                if (pole[fRow][fColumn+2] == 0 && pole[fRow - 1][fColumn+2] == 0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn + 2] = 0;
                    pole[fRow][fColumn + 2] = vid;
                    pole[fRow - 1][fColumn + 2] = vid;
                    flag = 1;
                }
                break;
            case 1:
                if (fColumn == 1) {
                    if (pole[fRow+2][fColumn] == 0 && pole[fRow + 2][fColumn+1] == 0) {
                        pole[fRow][fColumn] = 0;
                        pole[fRow + 2][fColumn-1] = 0;
                        pole[fRow + 2][fColumn] = vid;
                        pole[fRow + 2][fColumn+1] = vid;
                        flag = 0;
                    }
                }
                if (pole[fRow+1][fColumn - 2] == 0 && pole[fRow + 2][fColumn] == 0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn] = 0;
                    pole[fRow + 1][fColumn -2] = vid;
                    pole[fRow + 2][fColumn] = vid;
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
