package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_event);
    }

    public void onPictureClick(View view) {
        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);
    }
}