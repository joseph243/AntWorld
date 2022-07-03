package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Colony extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colony);
        getSupportActionBar().hide();
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