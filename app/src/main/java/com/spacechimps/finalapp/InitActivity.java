package com.spacechimps.finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

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
    private RequestQueue requestQueue;
    static int userId;
    static TextView error;
    static String user="";

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
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (image.getHeight() * 0.618 / 2));
                para.leftMargin = 20;
                para.rightMargin = 20;
                textUser.setLayoutParams(para);
                pass.setLayoutParams(para);

            }
        });

    }

    public void launchNewUser(View v) {
        Intent intent = new Intent(getApplicationContext(), NewUser.class);
        startActivity(intent);
    }

    public void login(View v) {
        InitActivity.error=(TextView) findViewById(R.id.error);
        MultiAutoCompleteTextView userView = (MultiAutoCompleteTextView) findViewById(R.id.user);
        MultiAutoCompleteTextView passwordView = (MultiAutoCompleteTextView) findViewById(R.id.password);
        InitActivity.user = userView.getText().toString();
        String password = passwordView.getText().toString();
        requestQueue = Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://spacechimps.ddns.net/controller.php?operation=1&user=" + user + "&password=" + password,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        int sucess = 0;
                        try {
                            sucess = (int) response.get("sucess");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (sucess == 1) {
                            try {
                                userId = Integer.parseInt((String) response.get("user_id"));
                                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                                intent.putExtra("user_id", userId);
                                intent.putExtra("username", InitActivity.user);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            InitActivity.error.setText("User and/or password incorrect");
                        }
                    }


                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }



    }




