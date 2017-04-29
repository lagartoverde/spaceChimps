package com.spacechimps.finalapp;

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

    static String user="";
    static String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

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
