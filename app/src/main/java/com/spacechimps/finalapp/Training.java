package com.spacechimps.finalapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tomás on 29/04/2017.
 */

public class Training extends AppCompatActivity {
    DefinitionArray array;
    int questionNumber = 0;
    int correctAnswers = 0;
    int question;
    Button option1;
    Button option2;
    Button option3;
    TextView statement;
    final Handler buttons = new Handler();

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
        setContentView(R.layout.training);
        array = (DefinitionArray) getIntent().getExtras().getSerializable("array");
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        statement = (TextView) findViewById(R.id.question);
        setInterface();

    }


    public void setInterface(){

        if (questionNumber < 29){
            question = (int) (Math.random() * 3);
            statement.setText(array.definitions[question + questionNumber].definition);
            option1.setText(array.definitions[0 + questionNumber].word);
            option2.setText(array.definitions[1 + questionNumber].word);
            option3.setText(array.definitions[2 + questionNumber].word);
            questionNumber+=3;
        }
        else{
            setContentView(R.layout.score);
            TextView text = (TextView) findViewById(R.id.score);
            text.setText("You have finished. Your score is " + Integer.toString(correctAnswers) + " correct answers");
            option1.setClickable(false);
            option2.setClickable(false);
            option3.setClickable(false);
        }
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
