package com.josephvanderzwart.antworld.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.josephvanderzwart.antworld.AntWorldApp;
import com.josephvanderzwart.antworld.game.Bonus;
import com.josephvanderzwart.antworld.game.Colony;
import com.josephvanderzwart.antworld.game.Entity;
import com.josephvanderzwart.antworld.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private TextView textViewAnts;
    private TextView textViewQueens;
    private TextView textViewGrowth;
    private TextView selectedEntityNameTextView;
    private TextView[] colonyMap = new TextView[100];
    private boolean mapIsDrawn = false;
    private Button buttonSendQueen;
    private Button buttonSetActiveColony;
    private int selectedEntityId = 0;

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
        setContentView(R.layout.activity_map);
        initializeHandler();
        drawMap();
        //assign text views:
        textViewAnts = (TextView) findViewById(R.id.ants);
        textViewQueens = (TextView) findViewById(R.id.queens);
        textViewGrowth = (TextView) findViewById(R.id.growth);
        selectedEntityNameTextView = (TextView) findViewById(R.id.selectedEntityNameTextView);
        selectedEntityNameTextView.setText("Home Colony");
        buttonSendQueen = (Button) findViewById(R.id.button7);
        buttonSendQueen.setVisibility(View.INVISIBLE);
        buttonSetActiveColony = (Button) findViewById(R.id.button8);
        buttonSetActiveColony.setVisibility(View.INVISIBLE);
    }

    public void onSendQueen(View view)
    {
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        mainApp.getGameRunner().sendQueen(selectedEntityId);
        drawMap();
        setColonyOutputDisplay();
    }

    public void onSetActiveColony(View view)
    {
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        mainApp.getGameRunner().setActiveColony(selectedEntityId);
    }

    private void setSelectedEntity(int index)
    {
        System.out.println("selection: " + index);
        selectedEntityId = index;
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        String cellDescription = "MapActivity.java : setActiveEntity: unrecognized entity";
        if (mainApp.getGameRunner().getEntityAt(index) == null)
        {
            cellDescription = "Empty Region " + index;
            setEmptyOutputDisplay();
        }
        else
        {
            //populated cell selected:
            Entity entityInThisCell = mainApp.getGameRunner().getEntityAt(index);
            Class entityType = entityInThisCell.getClass();
            if (Bonus.class.equals(entityType)) {
                cellDescription = (entityInThisCell.isEvil()? "Toxic Zone "  + index + ": Strength "
                        : "Bonus Resource " + index + ": Strength ");
                cellDescription = cellDescription +  + entityInThisCell.getStrength();
                setBonusOutputDisplay("" + entityInThisCell.getStrength());
            }
            if (Colony.class.equals(entityType)) {
                if (entityInThisCell.isPlayer())
                {
                    cellDescription = "My Colony "  + index +  ": Strength ";
                    setColonyOutputDisplay();
                }
                else
                {
                    cellDescription = "Enemy Colony "  + index +  ": Strength ";
                }
                cellDescription = cellDescription +  + entityInThisCell.getStrength();
            }
        }
        selectedEntityNameTextView.setText(cellDescription);
        drawMap();
    }

    private void drawMap() {
        //HARD-CODED MAP SIZE = 100
        //BUILD AND POPULATE MAP:
        TableLayout mapLayout = (TableLayout) findViewById(R.id.table1);
        mapLayout.removeAllViews();
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        List<Entity> activeEntities = mainApp.getGameRunner().getEntityList();
        HashMap<Integer, TextView> squares = new HashMap<>();

        for (int i = 0; i < 100; i++)
        {
            //defaults:
            colonyMap[i] = new TextView(this);
            colonyMap[i].setHeight(60);
            colonyMap[i].setWidth(60);
            colonyMap[i].setBackgroundColor(Color.GRAY);
            int finalI = i;
            colonyMap[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedEntity(finalI);
                }
            });
            squares.put(i, colonyMap[i]);
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
        mapIsDrawn = true;
    }

    public void onReturnClick(View view) {
        AntWorldApp mainApp = (AntWorldApp) getApplicationContext();
        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);
    }

    private void setEmptyOutputDisplay() {
        handler = null;
        textViewGrowth.setText(null);
        textViewQueens.setText(null);
        textViewAnts.setText("0");
        buttonSendQueen.setVisibility(View.VISIBLE);
        buttonSetActiveColony.setVisibility(View.INVISIBLE);
    }

    private void setBonusOutputDisplay(String inStrength) {
        handler = null;
        textViewGrowth.setText(null);
        textViewQueens.setText("Strength " + inStrength);
        textViewAnts.setText("0");
        buttonSendQueen.setVisibility(View.VISIBLE);
        buttonSetActiveColony.setVisibility(View.INVISIBLE);
    }

    private void setColonyOutputDisplay() {
        initializeHandler();
        buttonSendQueen.setVisibility(View.INVISIBLE);
        buttonSetActiveColony.setVisibility(View.VISIBLE);
    }
}