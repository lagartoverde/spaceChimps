package com.spacechimps.finalapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Tomás on 29/04/2017.
 */

public class Tests extends AppCompatActivity {
    DefinitionArray array;
    int questionNumber = 0;
    int correctAnswers = 0;
    int wrongAnswers = 0;
    int question;
    Button option1;
    Button option2;
    Button option3;
    TextView statement;
    final Handler miHandler = new Handler();
    final Handler buttons = new Handler();
    int timer = 99;
    boolean finish = false;




    protected void miHilo(){
        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(1000);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                miHandler.post(ejecutarAccion);
            }
        };
        t.start();
    }

    final Runnable ejecutarAccion = new Runnable() {
        @Override
        public void run() {
            if (timer == 0){
                setContentView(R.layout.score);
                TextView text = (TextView) findViewById(R.id.score);
                text.setText("The time is over. Your score is " + Integer.toString(computeScore()));
            }
            else if(!finish){
                TextView time = (TextView) findViewById(R.id.time);
                time.setText("Time: " + Integer.toString(timer));
                timer--;
                miHilo();
            }
        }

    };

    protected void cambiaColor(){
        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(500);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                buttons.post(changeColor);
            }
        };
        t.start();
    }

    final Runnable changeColor = new Runnable() {
        @Override
        public void run() {
            option1.setBackgroundColor(getResources().getColor(R.color.buttonBackground));
            option2.setBackgroundColor(getResources().getColor(R.color.buttonBackground));
            option3.setBackgroundColor(getResources().getColor(R.color.buttonBackground));
            option1.setClickable(true);
            option2.setClickable(true);
            option3.setClickable(true);
            setInterface();
            }
        };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        array = (DefinitionArray) getIntent().getExtras().getSerializable("array");
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        statement = (TextView) findViewById(R.id.question);
        miHilo();
        setInterface();

    }


    public void setInterface(){

        if (questionNumber < 29){
            question = (int) (Math.random() * 3);
            Log.v("Tag", Integer.toString(question));
            statement.setText(array.definitions[question + questionNumber].definition);
            option1.setText(array.definitions[0 + questionNumber].word);
            option2.setText(array.definitions[1 + questionNumber].word);
            option3.setText(array.definitions[2 + questionNumber].word);
            questionNumber+=3;
        }
        else{
            setContentView(R.layout.score);
            TextView text = (TextView) findViewById(R.id.score);
            text.setText("Congratulations, you have finished. Your score is " + Integer.toString(computeScore()));
            finish = true;
            option1.setClickable(false);
            option2.setClickable(false);
            option3.setClickable(false);
        }
    }

    public int computeScore(){
        return (int) (5000 * (correctAnswers - wrongAnswers / 2) / timer);
    }

    public void selectOptions(int option){

        if (option == question){
            switch (option){
                case 0:
                    option1.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
                case 1:
                    option2.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
                case 2:
                    option3.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
            }
            correctAnswers++;
        }
        else{
            switch (option){
                case 0:
                    option1.setBackgroundColor(getResources().getColor(R.color.redWrong));
                    break;
                case 1:
                    option2.setBackgroundColor(getResources().getColor(R.color.redWrong));
                    break;
                case 2:
                    option3.setBackgroundColor(getResources().getColor(R.color.redWrong));
                    break;
            }

            switch (question){
                case 0:
                    option1.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
                case 1:
                    option2.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
                case 2:
                    option3.setBackgroundColor(getResources().getColor(R.color.greenCorrect));
                    break;
            }
            wrongAnswers++;
        }
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        cambiaColor();
    }

    public void option3(View view) {
        selectOptions(2);
    }

    public void option2(View view) {
        selectOptions(1);
    }

    public void option1(View view) {
        selectOptions(0);
    }

    public void goBack(View view) {
        finish();
    }
}
