package com.example.myapplication.services;

import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.constants.Constants;
import com.example.myapplication.models.Shape;
import com.example.myapplication.models.impl.Dva;
import com.example.myapplication.models.impl.Kvadrat;
import com.example.myapplication.models.impl.L;
import com.example.myapplication.models.impl.Liniya;
import com.example.myapplication.models.impl.ObtatnayaL;
import com.example.myapplication.models.impl.Pyat;
import com.example.myapplication.models.impl.Treugolnik;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by dedo on 8/16/2016.
 */
public class Service {

    Constants con = new Constants();
    int[][] pole;
    ImageView[] imgNew;
    Random random = new Random();

    public Service(int[][] pole, ImageView[] imgNew) {
        this.pole = pole;
        this.imgNew = imgNew;
    }

    //метод перерисовки поля для тетриса
    public synchronized void backgr(int z) {
        for (int i = 0; i < con.ROW; i++)
            for (int j = 0; j < con.COLUMN; j++) {
                switch (pole[i][j]) {
                    case 0:
                        imgNew[j + i * con.COLUMN].setBackgroundResource(R.drawable.gray_middle);
                        break;
                    case 1:
                        imgNew[j + i * con.COLUMN].setBackgroundResource(R.drawable.blue_middle);
                        break;
                }
            }
    }

    //метод для проверки строк на удаление
    public void checkRow(){
        int cellNumber;
        for (int i=0;i<con.ROW;i++){
            cellNumber=0;
            for (int j=0;j<con.COLUMN;j++){
                if (pole[i][j]!=0){
                    cellNumber++;
                }
            }
            if (cellNumber==10){
                deleteRow(i);
            }
        }
    }

    //метод для удаления строки (принимает номер строки, который нужно удалить)
    public void deleteRow(int row){
        for (;row>0;row--){
            for(int j=0;j<con.COLUMN;j++){
                pole[row][j]=pole[row-1][j];
            }
        }
    }

    //метод для генерации массива с кодами фигур
    public void randomShape(int[] masShapes){
        for (int i=0;i<masShapes.length;i++){
            if (masShapes[i]==0) {masShapes[i]=random.nextInt(7)+1;}
        }
    }

    //метод преобразования кода в экземпляр фигуры
    public Shape chooseShape (int x){
        switch (x){
            case 1:
                return new Dva();
            case 2:
                return new Kvadrat();
            case 3:
                return  new L();
            case 4:
                return new Liniya();
            case 5:
                return new ObtatnayaL();
            case 6:
                return new Pyat();
            default:
                return new Treugolnik();
        }
    }

    //метод генерации новой фигуры
    public void nextShape(int[] masShapes){
        for (int i=0;i<masShapes.length;i++){
            if (i==masShapes.length-1){
                masShapes[i]=random.nextInt(7)+1;
            } else {
                masShapes[i]=masShapes[i+1];
            }
        }
    }


}
