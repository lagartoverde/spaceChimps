package com.spacechimps.finalapp;


import android.support.v7.app.AppCompatActivity;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */


import android.content.Intent;
import android.os.Bundle;

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

import java.util.Arrays;


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
        int user_id=getIntent().getExtras().getInt("user_id");
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=3&user="+user_id,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray jsondefinitions=response.getJSONArray("definitions");
                            if(jsondefinitions.length()<30){
                                //TODO: Mostrar mensajito
                            }else{
                                DefinitionArray definitions=new DefinitionArray(jsondefinitions.length());
                                for(int i=0;i<jsondefinitions.length();i++){
                                    JSONObject object=jsondefinitions.getJSONObject(i);
                                    String word=object.getString("word");
                                    String definitionString=object.getString("definition");
                                    Definition definition=new Definition(word,definitionString);
                                    definition.random=(int)Math.floor(Math.random()*jsondefinitions.length());
                                    definitions.definitions[i]=definition;
                                }
                                Arrays.sort(definitions.definitions);
                                DefinitionArray testDefinitions=new DefinitionArray(30);
                                for(int j=0;j<30;j++){
                                    testDefinitions.definitions[j]=definitions.definitions[j];
                                }
                                Intent intent=new Intent(getApplicationContext(),Tests.class);
                                intent.putExtra("array",testDefinitions);
                                startActivity(intent);
                            }
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

    public void practice(View v){
        int user_id=getIntent().getExtras().getInt("user_id");
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=3&user="+user_id,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray jsondefinitions=response.getJSONArray("definitions");
                            if(jsondefinitions.length()<30){
                                //TODO: Mostrar mensajito
                            }else{
                                DefinitionArray definitions=new DefinitionArray(jsondefinitions.length());
                                for(int i=0;i<jsondefinitions.length();i++){
                                    JSONObject object=jsondefinitions.getJSONObject(i);
                                    String word=object.getString("word");
                                    String definitionString=object.getString("definition");
                                    Definition definition=new Definition(word,definitionString);
                                    definition.random=(int)Math.floor(Math.random()*jsondefinitions.length());
                                    definitions.definitions[i]=definition;
                                }
                                Arrays.sort(definitions.definitions);
                                DefinitionArray testDefinitions=new DefinitionArray(30);
                                for(int j=0;j<30;j++){
                                    testDefinitions.definitions[j]=definitions.definitions[j];
                                }
                                Intent intent=new Intent(getApplicationContext(),Training.class);
                                intent.putExtra("array",testDefinitions);
                                startActivity(intent);
                            }
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

    public void ranking(View v){
        int user_id=getIntent().getExtras().getInt("user_id");
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=5&user="+user_id,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response){
                        try {
                            JSONArray array =response.getJSONArray("users");
                            UserPointsArray upArray=new UserPointsArray(array.length());
                            for(int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);
                                String username=object.getString("username");
                                int points=Integer.parseInt((String)object.get("points"));
                                upArray.users[i]=new UserPoints(username,points);
                            }
                            int rank=response.getInt("rank");
                            Intent intent=new Intent(getApplicationContext(),Ranking.class);
                            intent.putExtra("array",upArray);
                            intent.putExtra("rank",rank);
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
    public void crowdsourcing(View v){
        Intent intent=new Intent(getApplicationContext(),Crowdsourcing.class);
        startActivity(intent);
    }
}
