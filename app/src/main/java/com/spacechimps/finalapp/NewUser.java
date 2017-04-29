package com.spacechimps.finalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
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

/**
 * Created by Tomás on 29/04/2017.
 */

public class NewUser extends AppCompatActivity {
    static TextView error;
    RequestQueue requestQueue;
    ImageView image;
    MultiAutoCompleteTextView textUser;
    MultiAutoCompleteTextView pass;
    MultiAutoCompleteTextView pass2;

    MultiAutoCompleteTextView email;

    Button go;
    LinearLayout layout;


    protected void onCreate(Bundle savedInstanceState) {


        NewUser.error = (TextView) findViewById(R.id.error);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        image = (ImageView) findViewById(R.id.imageView5);
        textUser = (MultiAutoCompleteTextView) findViewById(R.id.user);
        pass = (MultiAutoCompleteTextView) findViewById(R.id.password);
        pass2 = (MultiAutoCompleteTextView) findViewById(R.id.password2);

        email = (MultiAutoCompleteTextView) findViewById(R.id.email);

        go = (Button) findViewById(R.id.login);

        image.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (image.getHeight() * 0.618 / 2));
                para.leftMargin = 20;
                para.rightMargin = 20;
                textUser.setLayoutParams(para);
                pass.setLayoutParams(para);
                pass2.setLayoutParams(para);
                email.setLayoutParams(para);
            }
        });
    }

    protected void checkNewUser(View v) {
            if (pass.getText().toString().equals(pass2.getText().toString())){
                String user=textUser.getText().toString();
                String password=pass.getText().toString();
                String emailS= email.getText().toString();
                requestQueue= Volley.newRequestQueue(this.getBaseContext());
                JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET,"http://spacechimps.ddns.net/controller.php?operation=0&user="+user+"&password="+password+"&email="+emailS,
                        new Response.Listener<JSONObject>(){
                            public void onResponse(JSONObject response){
                                try {
                                    int success=response.getInt("sucess");

                                    if(success==1){
                                        finish();
                                    }else{
                                        int error=response.getInt("error");
                                        if(error==1) {
                                            NewUser.error.setText("Duplicate Username");
                                        }else{
                                            NewUser.error.setText("Duplicate Email");
                                        }
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


            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Wrong Password");
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }




}
