package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class Colony extends AppCompatActivity {

    private TextView textView;

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colony);
        getSupportActionBar().hide();

        //counter text view:
        textView = (TextView) findViewById(R.id.counter);

        //init handler for posting message:
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                textView.setText(msg.getData().get("key").toString());
            }
        };
    }

    public void onPictureView(View view) {
        Intent intent = new Intent(this, PictureEvent.class);
        startActivity(intent);
    }

    public void onScoreButton(View view) {
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
    }

}