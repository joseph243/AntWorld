package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PictureEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_event);
        getSupportActionBar().hide();
    }
}