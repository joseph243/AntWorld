package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class ColonyActivity extends AppCompatActivity {

    private TextView textViewAnts;
    private TextView textViewQueens;
    private TextView textViewGrowth;

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colony);

        //counter text view:
        textViewAnts = (TextView) findViewById(R.id.ants);
        textViewQueens = (TextView) findViewById(R.id.queens);
        textViewGrowth = (TextView) findViewById(R.id.growth);

        //init handler for posting message:
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                textViewAnts.setText(msg.getData().get("ants").toString());
                textViewQueens.setText(msg.getData().get("queens").toString());
                textViewGrowth.setText(msg.getData().get("growth").toString());
            }
        };
    }

    public void onPictureView(View view) {
        Intent intent = new Intent(this, PictureActivity.class);
        startActivity(intent);
    }

    public void onScoreButton(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }
}