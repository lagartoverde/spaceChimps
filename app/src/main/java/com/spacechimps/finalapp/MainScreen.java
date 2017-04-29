package com.spacechimps.finalapp;


import android.support.v7.app.AppCompatActivity;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);


    }


    public void learn(View v){
        int user_id=getIntent().getExtras().getInt("user_id");
        int category=1;
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=2&user="+user_id+"&category="+category,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray array=response.getJSONArray("definitions");
                            DefinitionArray definitions=new DefinitionArray();
                            for(int i=0; i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);
                                String word=object.getString("word");
                                String definition=object.getString("definition");
                                definitions.definitions[i]=new Definition(word,definition);
                            }
                            Intent intent=new Intent(getApplicationContext(),LearnWords.class);
                            intent.putExtra("array",definitions);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
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
