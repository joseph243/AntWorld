package com.josephvanderzwart.antworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartButton(View view) {
        //open new game if applicable, and navigate to ColonyActivity screen:
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        mainApp.startGameRunner();

        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);
    }
}