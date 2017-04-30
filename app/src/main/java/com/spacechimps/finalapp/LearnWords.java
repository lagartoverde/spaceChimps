package com.spacechimps.finalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
 * Created by Tomás on 29/04/2017.
 */

public class LearnWords extends AppCompatActivity {
    DefinitionArray array;
    int index=0;
    RequestQueue requestQueue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning);
        array=(DefinitionArray) getIntent().getExtras().getSerializable("array");
        TextView word=(TextView)findViewById(R.id.word);
        TextView definition=(TextView) findViewById(R.id.definition);
        word.setText(array.definitions[index].word);
        definition.setText("\t\t\t"+array.definitions[index].definition);
    }
    public void next(View v){
        if(index==4){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Congratulations you have learnt 5 new words");
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            bindPack();
                            finish();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else{
            index++;
            TextView word=(TextView)findViewById(R.id.word);
            TextView definition=(TextView) findViewById(R.id.definition);
            word.setText(array.definitions[index].word);
            definition.setText("\t\t\t"+array.definitions[index].definition);
        }
    }
    public void bindPack(){
        int pack=getIntent().getExtras().getInt("pack");
        int user_id=getIntent().getExtras().getInt("user_id");
        requestQueue = Volley.newRequestQueue(this.getBaseContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://spacechimps.ddns.net/controller.php?operation=4&user=" + user_id + "&pack="+pack,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {

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
