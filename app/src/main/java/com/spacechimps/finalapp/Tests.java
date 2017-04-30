package com.spacechimps.finalapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tomás on 29/04/2017.
 */

public class Tests extends AppCompatActivity {
    DefinitionArray array;
    int questionNumber = 0;
    int correctAnswers = 0;
    int question;
    Button option1;
    Button option2;
    Button option3;
    TextView statement;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        array = (DefinitionArray) getIntent().getExtras().getSerializable("array");
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        statement = (TextView) findViewById(R.id.question);

        setInterface();

    }


    public void setInterface(){
        if (question < 9){
            question = (int) Math.random() * 3;
            statement.setText(array.definitions[question + questionNumber].definition);
            option1.setText(array.definitions[0 + questionNumber].word);
            option2.setText(array.definitions[1 + questionNumber].word);
            option3.setText(array.definitions[2 + questionNumber].word);
            questionNumber+=3;
        }
        else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Congratulations, your score is " + correctAnswers + " correct answers");
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
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
        setInterface();
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
}
