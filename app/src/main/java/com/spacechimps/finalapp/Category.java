package com.spacechimps.finalapp;

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

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class Category extends AppCompatActivity {
    RequestQueue requestQueue;
    int user_id;
    protected void onCreate(Bundle savedInstanceState){
        user_id=getIntent().getExtras().getInt("user_id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

    }
    public void general(View v){
        request(1);
    }
    public void nasa(View v){
        request(2);
    }
    public void physics(View v){
        request(3);
    }
    public void tech(View v){
        request(4);
    }
    public void tools(View v){
        request(5);
    }
    public void relevant(View v){
        request(6);
    }
    public void request(int id){
        requestQueue= Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=2&user="+user_id+"&category="+id,
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
