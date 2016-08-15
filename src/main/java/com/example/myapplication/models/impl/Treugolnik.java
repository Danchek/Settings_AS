package com.example.myapplication.models.impl;
/////// Shapre ready
import android.util.Log;
import android.widget.Switch;

import com.example.myapplication.constants.Constants;
import com.example.myapplication.models.Shape;

/**
 * Created by User on 10.07.2016.
 */
public class Treugolnik implements Shape {
    public static final String TAG = "myLogs";
    int step=0, fRow=0, fColumn=0;
    Constants con = new Constants();


    @Override
    public int create(int[][] pole) {
        if (pole[1][5] == 0 && pole[0][5] == 0 && pole[1][4] == 0 && pole[1][6] == 0) {
            pole[1][5] = con.TREUG;
            pole[0][5] = con.TREUG;
            pole[0][4] = con.TREUG;
            pole[0][6] = con.TREUG;
            return 0;
        } else return 8;
    }

    @Override
    public boolean down(int[][] pole, int currentState) {
        identFirstBlock(pole);
        if (step==0){step++; return true;}
        step++;
        boolean flag=false;
        Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if (fRow==18){return false;}
                if(pole[fRow+1][fColumn] == 0 && pole[fRow+1][fColumn + 2] == 0 && pole[fRow+2][fColumn+1]==0) {
                    pole[fRow + 1][fColumn] = con.TREUG;
                    pole[fRow + 1][fColumn+2] = con.TREUG;
                    pole[fRow + 2][fColumn+1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    pole[fRow][fColumn + 1] = 0;
                    pole[fRow][fColumn+2] = 0;
                    flag=true;
                }
                break;
            case 1:
                if (fRow==17){return false;}
                if(pole[fRow+2][fColumn-1]==0 && pole[fRow+3][fColumn]==0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn - 1] = 0;
                    pole[fRow + 2][fColumn - 1] = con.TREUG;
                    pole[fRow + 3][fColumn] = con.TREUG;
                    flag = true;
                }
                break;
            case 2:
                if (fRow==18){return false;}
                if(pole[fRow+2][fColumn-1]==0 && pole[fRow+2][fColumn]==0 && pole[fRow+2][fColumn+1]==0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn - 1] = 0;
                    pole[fRow + 1][fColumn + 1] = 0;
                    pole[fRow + 2][fColumn - 1] = con.TREUG;
                    pole[fRow + 2][fColumn] = con.TREUG;
                    pole[fRow + 2][fColumn + 1] = con.TREUG;
                    flag=true;
                }
                break;
            case 3:
                if (fRow==17){return false;}
                if (pole[fRow+2][fColumn+1]==0 &&pole[fRow+3][fColumn]==0) {
                    pole[fRow][fColumn] = 0;
                    pole[fRow + 1][fColumn + 1] = 0;
                    pole[fRow + 2][fColumn + 1] = con.TREUG;
                    pole[fRow + 3][fColumn] = con.TREUG;
                    flag=true;
                }
                break;
        }
        return flag;
    }


    @Override
    public int turnLeft(int[][] pole, int currentState) {
        int flag=currentState;
        identFirstBlock(pole);
        if (fRow==0){return currentState;}
        Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if(pole[fRow-1][fColumn+1] == 0) {
                    pole[fRow-1][fColumn+1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    flag = 3;
                }
                break;
            case 1:
                if(fColumn==9){
                    if(pole[fRow+1][fColumn-2] == 0 && pole[fRow+2][fColumn-1] == 0){
                        pole[fRow+1][fColumn-2] = con.TREUG;
                        pole[fRow+2][fColumn-1] = con.TREUG;
                        pole[fRow][fColumn] = 0;
                        pole[fRow+2][fColumn] = 0;
                        flag = 0;
                    }
                }
                else if(pole[fRow+1][fColumn+1] == 0) {
                    pole[fRow+1][fColumn+1] = con.TREUG;
                    pole[fRow+0][fColumn+0] = 0;
                    flag = 0;
                }
                break;
            case 2:
                if(pole[fRow+2][fColumn] == 0) {
                    pole[fRow+2][fColumn] = con.TREUG;
                    pole[fRow+1][fColumn+1] = 0;
                    flag = 1;
                }
                break;
            case 3:
                if (fColumn==0){
                    if(pole[fRow][fColumn+1]==0 && pole[fRow+1][fColumn+2]==0){
                        pole[fRow][fColumn+1] = con.TREUG;
                        pole[fRow+1][fColumn+2] = con.TREUG;
                        pole[fRow][fColumn] = 0;
                        pole[fRow+2][fColumn] = 0;
                        flag = 2;
                    }
                }
                else if(pole[fRow+1][fColumn-1] == 0) {
                    pole[fRow+1][fColumn-1] = con.TREUG;
                    pole[fRow+2][fColumn] = 0;
                    flag = 2;
                }
                break;
        }
        return flag;
    }

    @Override
    public int turnRight(int[][] pole, int currentState) {
        identFirstBlock(pole);
        int flag=currentState;
        if (fRow==0){return currentState;}
        Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if(pole[fRow-1][fColumn+1] == 0) {
                    pole[fRow-1][fColumn+1] = con.TREUG;
                    pole[fRow][fColumn+2] = 0;
                    flag = 1;
                }
                break;
            case 1:
                if(fColumn==9){
                    if(pole[fRow+1][fColumn-2] == 0 && pole[fRow][fColumn-1] == 0){
                        pole[fRow+1][fColumn-2] = con.TREUG;
                        pole[fRow][fColumn-1] = con.TREUG;
                        pole[fRow][fColumn] = 0;
                        pole[fRow+2][fColumn] = 0;
                        flag = 2;
                    }
                }
                else if(pole[fRow+1][fColumn+1] == 0) {
                    pole[fRow+1][fColumn+1] = con.TREUG;
                    pole[fRow+2][fColumn+0] = 0;
                    flag = 2;
                }
                break;
            case 2:
                if(pole[fRow+2][fColumn] == 0) {
                    pole[fRow+2][fColumn] = con.TREUG;
                    pole[fRow+1][fColumn-1] = 0;
                    flag = 3;
                }
                break;
            case 3:
                if (fColumn==0){
                    if(pole[fRow+2][fColumn+1]==0 && pole[fRow+1][fColumn+2]==0){
                        pole[fRow+2][fColumn+1] = con.TREUG;
                        pole[fRow+1][fColumn+2] = con.TREUG;
                        pole[fRow][fColumn] = 0;
                        pole[fRow+2][fColumn] = 0;
                        flag = 0;
                    }
                }
                else if(pole[fRow+1][fColumn-1] == 0) {
                    pole[fRow+1][fColumn-1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    flag = 0;
                }
                break;
        }
        return flag;
    }

    @Override
    public boolean left(int[][] pole, int currentState) {
        boolean flag=false;
        identFirstBlock(pole);
        Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if (fColumn==0){return flag;}
                if(pole[fRow][fColumn-1] == 0 && pole[fRow+1][fColumn] == 0) {
                    pole[fRow][fColumn-1] = con.TREUG;
                    pole[fRow+1][fColumn] = con.TREUG;
                    pole[fRow+1][fColumn + 1] = 0;
                    pole[fRow][fColumn+2] = 0;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==1){return flag;}
                if(pole[fRow][fColumn-1] == 0 && pole[fRow+1][fColumn-2] == 0 && pole[fRow+2][fColumn-1] == 0) {
                    pole[fRow][fColumn-1] = con.TREUG;
                    pole[fRow+1][fColumn-2] = con.TREUG;
                    pole[fRow+2][fColumn-1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    pole[fRow+1][fColumn] = 0;
                    pole[fRow+2][fColumn] = 0;
                    flag=true;
                }
                break;
            case 2:
                if (fColumn==1){return flag;}
                if(pole[fRow][fColumn-1] == 0 && pole[fRow+1][fColumn-2] == 0) {
                    pole[fRow][fColumn-1] = con.TREUG;
                    pole[fRow+1][fColumn-2] = con.TREUG;
                    pole[fRow+1][fColumn + 1] = 0;
                    pole[fRow][fColumn] = 0;
                    flag=true;
                }
                break;
            case 3:
                if (fColumn==0){return flag;}
                if(pole[fRow][fColumn-1] == 0 && pole[fRow+1][fColumn-1] == 0 && pole[fRow+2][fColumn-1] == 0) {
                    pole[fRow][fColumn-1] = con.TREUG;
                    pole[fRow+1][fColumn-1] = con.TREUG;
                    pole[fRow+2][fColumn-1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    pole[fRow+1][fColumn+1] = 0;
                    pole[fRow+2][fColumn] = 0;
                    flag=true;
                }
                break;
        }
        return flag;
    }

    @Override
    public boolean right(int[][] pole, int currentState) {
        boolean flag=false;
        identFirstBlock(pole);
        Log.d(TAG,fRow+" "+fColumn+" "+currentState);
        switch (currentState) {
            case 0:
                if (fColumn==7){return flag;}
                if(pole[fRow][fColumn+3] == 0 && pole[fRow+1][fColumn+2] == 0) {
                    pole[fRow][fColumn+3] = con.TREUG;
                    pole[fRow+1][fColumn+2] = con.TREUG;
                    pole[fRow+1][fColumn + 1] = 0;
                    pole[fRow][fColumn] = 0;
                    flag=true;
                }
                break;
            case 1:
                if (fColumn==9){return flag;}
                if(pole[fRow][fColumn+1] == 0 && pole[fRow+1][fColumn+1] == 0 && pole[fRow+2][fColumn+1] == 0) {
                    pole[fRow][fColumn+1] = con.TREUG;
                    pole[fRow+1][fColumn+1] = con.TREUG;
                    pole[fRow+2][fColumn+1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    pole[fRow+1][fColumn-1] = 0;
                    pole[fRow+2][fColumn] = 0;
                    flag=true;
                }
                break;
            case 2:
                if (fColumn==8){return flag;}
                if(pole[fRow][fColumn+1] == 0 && pole[fRow+1][fColumn+2] == 0) {
                    pole[fRow][fColumn+1] = con.TREUG;
                    pole[fRow+1][fColumn+2] = con.TREUG;
                    pole[fRow+1][fColumn - 1] = 0;
                    pole[fRow][fColumn] = 0;
                    flag=true;
                }
                break;
            case 3://///
                if (fColumn==8){return flag;}
                if(pole[fRow][fColumn+1] == 0 && pole[fRow+1][fColumn+2] == 0 && pole[fRow+2][fColumn+1] == 0) {
                    pole[fRow][fColumn+1] = con.TREUG;
                    pole[fRow+1][fColumn+2] = con.TREUG;
                    pole[fRow+2][fColumn+1] = con.TREUG;
                    pole[fRow][fColumn] = 0;
                    pole[fRow+1][fColumn] = 0;
                    pole[fRow+2][fColumn] = 0;
                    flag=true;
                }
                break;
        }
        return flag;
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
