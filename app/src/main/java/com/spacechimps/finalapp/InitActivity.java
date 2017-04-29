package com.spacechimps.finalapp;


import android.content.Intent;
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

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.VolleyError;




public class InitActivity extends AppCompatActivity {
    ImageView image;
    MultiAutoCompleteTextView textUser;
    MultiAutoCompleteTextView pass;
    Button go;
    LinearLayout layout;
    private RequestQueue requestQueue;
    static int userId;

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

        image.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int) (image.getHeight() * 0.618 / 2));
                para.leftMargin = 20;
                para.rightMargin = 20;
                textUser.setLayoutParams(para);
                pass.setLayoutParams(para);

            }
        });

    }

    public void login(View v){
        MultiAutoCompleteTextView userView=(MultiAutoCompleteTextView)findViewById(R.id.user);
        MultiAutoCompleteTextView passwordView=(MultiAutoCompleteTextView)findViewById(R.id.password);
        user=userView.getText().toString();
        password=passwordView.getText().toString();
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=1&user="+user+"&password="+password,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        int sucess= 0;
                        try {
                            sucess = (int)response.get("sucess");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(sucess==1) {
                            try {
                                userId=Integer.parseInt((String)response.get("user_id"));
                                Intent intent=new Intent(getApplicationContext(),MainScreen.class);
                                intent.putExtra("user_id",userId);
                                intent.putExtra("username",user);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            
                        }
                    }


                },
                new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
        });
        requestQueue.add(request);
    }


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

