package com.spacechimps.finalapp;

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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oscarrodriguez on 30/04/2017.
 */

public class Crowsourcing extends AppCompatActivity {
    static public TextView feedback;
    private RequestQueue requestQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crowsourcing);
    }
    public void send(View v){
        TextView wordT=(TextView) findViewById(R.id.word);
        TextView definitionT=(TextView) findViewById(R.id.definition);
        Crowsourcing.feedback=(TextView) findViewById(R.id.feedback);
        String word=wordT.getText().toString();
        String definition=definitionT.getText().toString();
        requestQueue = Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://spacechimps.ddns.net/controller.php?operation=7&word=" + word + "&definition=" + definition,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                           Crowsourcing.feedback.setText("Thanks for your input");

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
