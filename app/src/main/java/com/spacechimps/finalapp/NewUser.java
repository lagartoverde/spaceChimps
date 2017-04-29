package com.spacechimps.finalapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by Tomás on 29/04/2017.
 */

public class NewUser extends AppCompatActivity {


    ImageView image;
    MultiAutoCompleteTextView textUser;
    MultiAutoCompleteTextView pass;
    MultiAutoCompleteTextView pass2;

    MultiAutoCompleteTextView email;

    Button go;
    LinearLayout layout;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        image = (ImageView) findViewById(R.id.imageView5);
        textUser = (MultiAutoCompleteTextView) findViewById(R.id.user);
        pass = (MultiAutoCompleteTextView) findViewById(R.id.password);
        pass2 = (MultiAutoCompleteTextView) findViewById(R.id.password2);

        email = (MultiAutoCompleteTextView) findViewById(R.id.email);

        go = (Button) findViewById(R.id.login);

        image.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (image.getHeight() * 0.618 / 2));
                para.leftMargin = 20;
                para.rightMargin = 20;
                textUser.setLayoutParams(para);
                pass.setLayoutParams(para);
                pass2.setLayoutParams(para);
                email.setLayoutParams(para);
            }
        });
    }

    protected void checkNewUser(View v) {
            if (pass.getText().toString().equals(pass2.getText().toString())){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("New User Created");
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

            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Wrong Password");
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }




}
