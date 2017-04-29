package com.spacechimps.finalapp;


import android.support.v7.app.AppCompatActivity;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






public class MainScreen extends AppCompatActivity{
    RequestQueue requestQueue;

    TextView welcome;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        welcome = (TextView) findViewById(R.id.welcome);
        String userID = getIntent().getExtras().getString("username");
        welcome.setText("Welcome, " + userID + ". Choose your desired playing game");

    }


    public void learn(View v){
        Intent intent=new Intent(getApplicationContext(),Category.class);
        intent.putExtra("user_id",getIntent().getExtras().getInt("user_id"));
        startActivity(intent);
    }

    public void compete(View v){

    }

    public void practice(View v){

    }
}
