package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.myapplication.constants.Constants;

import com.example.myapplication.models.Shape;
import com.example.myapplication.models.impl.Kvadrat;
import com.example.myapplication.models.impl.Liniya;


import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "myLogs";
    TableLayout tableLayout;
    TableRow []tableRows;
    ImageView []imgNew;
//    final int ROW=15, COLUMN=8, NUMER_OF_ELEMEMHT=ROW*COLUMN;
    int [][]pole;
    int currentState;
    Handler handler;
    Thread thread;
    Constants con = new Constants();
    ImageButton buttonR, buttonU,buttonL,buttonD, buttonB;
    Shape shape;
    Vector vector = new Vector(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int numberFields=0;
        pole= new int[con.ROW][con.COLUMN];
        vector.add(pole);
        //Log.d(TAG, "MainActivityCreate");
        tableLayout = (TableLayout)findViewById(R.id.TableLayout);
        tableRows = new TableRow[con.ROW];
        imgNew = new ImageView[con.NUMER_OF_ELEMEMHT];

        buttonD = (ImageButton) findViewById(R.id.imageButtonDown);
        //buttonD.setOnClickListener(this);
        //buttonD.setOnLongClickListener(this);
        buttonD.setOnTouchListener(this);
        buttonR = (ImageButton) findViewById(R.id.imageButtonRight);
        buttonR.setOnClickListener(this);
        buttonU = (ImageButton) findViewById(R.id.imageButtonUp);
        buttonU.setOnClickListener(this);
        buttonL = (ImageButton) findViewById(R.id.imageButtonLeft);
        buttonL.setOnClickListener(this);
        buttonB = (ImageButton) findViewById(R.id.imageButtonBig);
        buttonB.setOnClickListener(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                backgr(msg.what);
            }

        };

        for (int i =0;i<con.NUMER_OF_ELEMEMHT;i++){
            imgNew[i] = new ImageView(this);
            imgNew[i].setId(i);
            imgNew[i].setBackgroundResource(R.drawable.gray_middle);
            imgNew[i].setLayoutParams(new TableRow.LayoutParams(-1,-1,1.0f));
        }
        for (int i=0;i<con.ROW;i++){
            tableRows[i] = new TableRow(this);
            tableRows[i].setLayoutParams(new TableLayout.LayoutParams(-1,-1,1.0f));
            for(int j=0;j<con.COLUMN;j++){
                tableRows[i].addView(imgNew[numberFields]);numberFields++;
            }
        }
        for(int i =0;i<con.ROW;i++){
            tableLayout.addView(tableRows[i]);
        }
        for (int i=0;i<con.ROW;i++)
            for (int j=0;j<con.COLUMN;j++){
                pole[i][j]=0;
            }
        thread();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonDown:
                shape.down(pole,currentState); backgr(currentState);
                Toast.makeText(this,"Down",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonLeft:
                shape.left(pole, currentState); backgr(currentState);
                Toast.makeText(this,"Left",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonRight:
                shape.right(pole, currentState); backgr(currentState);
                Toast.makeText(this,"Right",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonUp:
                currentState=shape.turnLeft(pole,currentState); backgr(currentState);
                Toast.makeText(this,"Up",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonBig:
                currentState=shape.turnRight(pole,currentState); backgr(currentState);
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.imageButtonDown:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //Toast.makeText(this,"DOWN",Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        //Toast.makeText(this,"UP",Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
        return false;
    }

    synchronized void  backgr(int z){


        for (int i=0;i<con.ROW;i++)
            for (int j=0;j<con.COLUMN;j++){
               // Log.d(TAG, "pole["+i+"]["+j+"] = "+pole[i][j]);
                switch (pole[i][j]){
                    case 0: imgNew[j+i*con.COLUMN].setBackgroundResource(R.drawable.gray_middle);
                        //Log.d(TAG, "00000000000000000000000pole["+i+"]["+j+"] = "+pole[i][j]+" "+(i+j*con.COLUMN));
                       // imgNew[i+j].jumpDrawablesToCurrentState();
                        break;
                    case 1: imgNew[j+i*con.COLUMN].setBackgroundResource(R.drawable.blue_middle);
                        //Log.d(TAG, "111111111111111111111111pole["+i+"]["+j+"] = "+pole[i][j]+" "+(i+j*con.COLUMN));
                       // imgNew[i+j].jumpDrawablesToCurrentState();
                        break;
                }
            }
    }

    void thread(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                boolean flag=true;
                //Service service = new ServiceImpl(new TreugolnikDAOFactoryCreateImpl());
                shape = new Liniya();
                currentState = shape.create(pole);
                int count=0;
                backgr(currentState);
                //Log.d(TAG, String.valueOf(service.down(pole,currentState)));
                while (flag){
                    try {
                        flag = shape.down(pole,currentState);
                        if (count==0) backgr(0);
                        count++;
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        flag = false;
                    }
                    handler.sendEmptyMessage(currentState);
                }

            }
        });
        thread.start();
    }


}
