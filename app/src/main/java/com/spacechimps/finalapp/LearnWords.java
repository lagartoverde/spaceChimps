package com.spacechimps.finalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tomás on 29/04/2017.
 */

public class LearnWords extends AppCompatActivity {
    DefinitionArray array;
    int index=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);
        array=(DefinitionArray) getIntent().getExtras().getSerializable("array");
        TextView word=(TextView)findViewById(R.id.word);
        TextView definition=(TextView) findViewById(R.id.definition);
        word.setText(array.definitions[index].word);
        definition.setText(array.definitions[index].definition);
    }
    public void next(View v){
        if(index==4){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Congratulations you have learn 5 new words");
            AlertDialog alert=builder.create();
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                    finish();
                }
            });
            alert.show();
        }else{
            index++;
            TextView word=(TextView)findViewById(R.id.word);
            TextView definition=(TextView) findViewById(R.id.definition);
            word.setText(array.definitions[index].word);
            definition.setText(array.definitions[index].definition);
        }
    }

}
