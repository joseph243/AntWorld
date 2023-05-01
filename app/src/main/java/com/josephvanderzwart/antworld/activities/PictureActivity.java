package com.josephvanderzwart.antworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.josephvanderzwart.antworld.AntWorldApp;
import com.josephvanderzwart.antworld.R;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_event);
    }

    public void onPictureClick(View view) {
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
       // mainApp.getGameRunner().getColony().killAnts(10);

        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);


    }
}