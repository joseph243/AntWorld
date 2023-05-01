package com.josephvanderzwart.antworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.josephvanderzwart.antworld.AntWorldApp;
import com.josephvanderzwart.antworld.R;

public class ColonyActivity extends AppCompatActivity {

    private TextView textViewAnts;
    private TextView textViewQueens;
    private TextView textViewGrowth;

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    private void initializeHandler() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colony);
        initializeHandler();

        //counter text view:
        textViewAnts = (TextView) findViewById(R.id.ants);
        textViewQueens = (TextView) findViewById(R.id.queens);
        textViewGrowth = (TextView) findViewById(R.id.growth);
    }

    public void onPictureView(View view) {
        Intent intent = new Intent(this, PictureActivity.class);
        startActivity(intent);
    }

    public void onAddQueen(View view) {
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        if (mainApp.getGameRunner().getActiveColony().getAnts() > 100) {
            mainApp.getGameRunner().getActiveColony().addQueen();
            mainApp.getGameRunner().getActiveColony().killAnts(100);
        }
    }

    public void onMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void onScoreButton(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }
}