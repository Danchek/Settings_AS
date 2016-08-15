package com.example.myapplication.models.impl;

import android.util.Log;

import com.example.myapplication.constants.Constants;
import com.example.myapplication.models.Shape;

/**
 * Created by User on 10.07.2016.
 */
public class Kvadrat implements Shape {
    public static final String TAG = "myLogs";
    Constants con = new Constants();
    int step=0;
    int fRow = 0, fColumn=0;
    @Override
    public int create(int[][] pole) {
        if (pole[1][5] == 0 && pole[0][5] == 0 && pole[1][4] == 0 && pole[0][4] == 0) {
            pole[0][5] = con.KVAD;
            pole[1][5] = con.KVAD;
            pole[0][4] = con.KVAD;
            pole[1][4] = con.KVAD;
            return 0;
        } else return 8;
    }

    @Override
    public boolean down(int[][] pole, int currentState) {
        Log.d(TAG, "step = "+step);
        if (step==0){step++; return true;}
        boolean flag = false;
        identFirstBlock(pole);
        if(fRow==18)return flag;
        if(pole[fRow+2][fColumn]==0 && pole[fRow+2][fColumn+1]==0) {
            pole[fRow][fColumn] = 0;
            pole[fRow][fColumn + 1] = 0;
            pole[fRow + 2][fColumn] = con.KVAD;
            pole[fRow + 2][fColumn + 1] = con.KVAD;
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean left(int[][] pole, int currentState) {
        boolean flag = false;
        identFirstBlock(pole);
        //Log.d(TAG,"left "+fColumn);
        if(fColumn==0){return flag;}
        if(pole[fRow][fColumn-1]==0 && pole[fRow+1][fColumn-1]==0) {
            pole[fRow][fColumn+1] = 0;
            pole[fRow+1][fColumn+1] = 0;
            pole[fRow][fColumn-1] = con.KVAD;
            pole[fRow+1][fColumn - 1] = con.KVAD;
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean right(int[][] pole, int currentState) {
        boolean flag = false;
        identFirstBlock(pole);
        //Log.d(TAG,"right "+fColumn);
        if(fColumn==8){return flag;}
        if(pole[fRow][fColumn+2]==0&&pole[fRow+1][fColumn+2]==0) {
            pole[fRow][fColumn] = 0;
            pole[fRow+1][fColumn] = 0;
            pole[fRow][fColumn+2] = con.KVAD;
            pole[fRow+1][fColumn + 2] = con.KVAD;
            flag=true;
        }
        return flag;
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
