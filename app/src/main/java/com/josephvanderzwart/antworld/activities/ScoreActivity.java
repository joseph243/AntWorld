package com.josephvanderzwart.antworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.josephvanderzwart.antworld.R;

public class ScoreActivity extends AppCompatActivity {

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    private void initializeHandler() {
        //init handler for posting message:
        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg){
//                textViewAnts.setText(msg.getData().get("ants").toString());
//                textViewQueens.setText(msg.getData().get("queens").toString());
//                textViewGrowth.setText(msg.getData().get("growth").toString());
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initializeHandler();
    }
}