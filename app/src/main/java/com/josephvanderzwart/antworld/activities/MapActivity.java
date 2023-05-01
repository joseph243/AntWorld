package com.josephvanderzwart.antworld.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.josephvanderzwart.antworld.AntWorldApp;
import com.josephvanderzwart.antworld.game.Entity;
import com.josephvanderzwart.antworld.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private static Handler handler;
    private TextView textViewAnts;
    private TextView textViewQueens;
    private TextView textViewGrowth;
    private TextView selectedEntityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initializeHandler();
        TableLayout mapLayout = (TableLayout) findViewById(R.id.table1);
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        List<Entity> activeEntities = mainApp.getGameRunner().getEntityList();
        HashMap<Integer, TextView> squares = new HashMap<>();

        //populate text views:
        textViewAnts = (TextView) findViewById(R.id.ants);
        textViewQueens = (TextView) findViewById(R.id.queens);
        textViewGrowth = (TextView) findViewById(R.id.growth);
        selectedEntityNameTextView = (TextView) findViewById(R.id.selectedEntityNameTextView);
        selectedEntityNameTextView.setText("Colony Name Here");


        //HARD-CODED MAP SIZE:
        for (int i = 0; i < 100; i++)
        {
            //defaults:
            TextView tv = new TextView(this);
            tv.setHeight(60);
            tv.setWidth(60);
            tv.setBackgroundColor(Color.GRAY);
            squares.put(i, tv);
        }

        for (Entity e : activeEntities)
        {
            System.out.println("start debug with size= " + activeEntities.size());
            System.out.println(e.toString());
            if (!e.isEvil())
            {
                TextView tv = squares.get(e.getLoc());
                tv.setText("" + e.getStrength());
                tv.setBackgroundColor(Color.GREEN);
                squares.put(e.getLoc(), tv);
            }
            if (e.isEvil())
            {
                TextView tv = squares.get(e.getLoc());
                tv.setText("" + e.getStrength());
                tv.setBackgroundColor(Color.RED);
                squares.put(e.getLoc(), tv);
            }
            if (e.isPlayer())
            {
                TextView tv = squares.get(e.getLoc());
                tv.setText("" + e.getStrength());
                tv.setBackgroundColor(Color.CYAN);
                squares.put(e.getLoc(), tv);
            }
        }

        int j = 0;
        for (int y = 0; y < 10; y++)
        {
            TableRow tr = new TableRow(this);
            for (int x = 0; x < 10; x++)
            {
                 tr.addView(squares.get(j));
                 j++;
            }
            tr.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            mapLayout.addView(tr);
        }

    }

    public void onReturnClick(View view) {
        AntWorldApp mainApp = (AntWorldApp) getApplicationContext();
        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);
    }

    private void initializeHandler() {
        //init handler for posting message:
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                textViewAnts.setText(msg.getData().get("ants").toString());
                textViewQueens.setText(msg.getData().get("queens").toString());
                textViewGrowth.setText(msg.getData().get("growth").toString());
                selectedEntityNameTextView.setText(msg.getData().get("selectedEntityName").toString());
            }
        };
    }
}