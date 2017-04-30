package com.spacechimps.finalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class Ranking extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        UserPointsArray array=(UserPointsArray) getIntent().getExtras().getSerializable("array");
        TableLayout table=(TableLayout)findViewById(R.id.table);
        for(int i=0;i<array.users.length;i++){
            TextView username=new TextView(this);
            TextView points=new TextView(this);
            TextView position=new TextView(this);
            TableRow row=new TableRow(this);
            row.addView(position);
            row.addView(username);
            row.addView(points);
            table.addView(row);
            Log.v("Success",array.users[i].username);
            username.setText(array.users[i].username);
            points.setText(Integer.toString(array.users[i].points));
            position.setText(Integer.toString(i+1)+".");
            username.setTextSize(30);
            points.setTextSize(30);
            position.setTextSize(30);
            TableRow.LayoutParams llp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            llp.setMargins(100, 0, 100, 0);
            points.setLayoutParams(llp);
            username.setLayoutParams(llp);
            position.setLayoutParams(llp);
        }
        TextView ranking=(TextView) findViewById(R.id.rank);
        int rank=getIntent().getExtras().getInt("rank");
        if(rank==-1){
            ranking.setText("You are not yet in the ranking");
        }else {
            ranking.setText("You are the number " + rank + " in the global ranking");
        }
    }
}
