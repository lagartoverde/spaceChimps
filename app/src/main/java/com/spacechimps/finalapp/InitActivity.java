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

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MultiAutoCompleteTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.view.View;
import android.util.Log;
import android.os.StrictMode;



public class InitActivity extends AppCompatActivity {
    ImageView image;
    MultiAutoCompleteTextView textUser;
    MultiAutoCompleteTextView pass;
    Button go;
    LinearLayout layout;

    static String user="";
    static String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        image = (ImageView) findViewById(R.id.imageView5);
        textUser = (MultiAutoCompleteTextView) findViewById(R.id.user);
        pass = (MultiAutoCompleteTextView) findViewById(R.id.password);
        go = (Button) findViewById(R.id.login);

        layout = (LinearLayout) findViewById(R.id.layout);

        image.post(new Runnable() {
            @Override
            public void run() {
                textUser.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 / 2)));
                pass.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 / 2)));
                //LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 * 0.618));
                //param.gravity = Gravity.CENTER_HORIZONTAL;
                //layout.setGravity(Gravity.CENTER_HORIZONTAL);
                //go.setLayoutParams(param);
            }
        });



    }

    public void login(View v){
        MultiAutoCompleteTextView user=(MultiAutoCompleteTextView)findViewById(R.id.user);
        MultiAutoCompleteTextView password=(MultiAutoCompleteTextView)findViewById(R.id.password);
        InitActivity.user=user.toString();
        InitActivity.password=user.toString();
        new getLogin().doInBackground();
    }
    private class getLogin extends AsyncTask<Void,Void,Void>{
        InputStream in;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url=new URL("http://spacechimps.ddns.net/controller.php?operation=1&user="+InitActivity.user+"&password="+InitActivity.password);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                in=new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void a){
            String reponse=convertStreamToString(in);
            Log.v("success",reponse);
        }

        public  String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

    }


}