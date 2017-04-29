package com.spacechimps.finalapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;

public class InitActivity extends AppCompatActivity {
    ImageView image;
    MultiAutoCompleteTextView user;
    MultiAutoCompleteTextView pass;
    Button go;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginscreen);

        image = (ImageView) findViewById(R.id.imageView5);
        user = (MultiAutoCompleteTextView) findViewById(R.id.user);
        pass = (MultiAutoCompleteTextView) findViewById(R.id.pass);
        go = (Button) findViewById(R.id.login);

        layout = (LinearLayout) findViewById(R.id.layout);

        image.post(new Runnable() {
            @Override
            public void run() {
                user.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 / 2)));
                pass.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 / 2)));
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams((int)(image.getWidth() * 0.618 * 0.618),(int) (image.getHeight() * 0.618 * 0.618));
                param.gravity = Gravity.CENTER_HORIZONTAL;
                layout.setGravity(Gravity.CENTER_HORIZONTAL);
                go.setLayoutParams(param);
            }
        });




    }


}