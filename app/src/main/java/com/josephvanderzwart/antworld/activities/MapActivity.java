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

import java.util.List;

public class MapActivity extends AppCompatActivity {

    private static Handler handler;

    public static Handler getHandler(){
        return handler;
    }

    private void initializeHandler() {
        //init handler for posting message:
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        TableLayout mapLayout = (TableLayout) findViewById(R.id.table1);
        AntWorldApp mainApp = (AntWorldApp)getApplicationContext();
        List<Entity> map = mainApp.getGameRunner().getEntityList();

        for (int y = 0; y < 10; y++)
        {
            TableRow tr = new TableRow(this);
            for (int x = 0; x < 10; x++)
            {
                TextView tv = new TextView(this);
                tv.setHeight(60);
                tv.setWidth(60);
                //who lives at these coord?
                //resident[] = {type, strength};
                int[] resident = determineResidency(map, x, y);
                if (resident[0] == 3)
                {
                    //empty:
                    tv.setBackgroundColor(Color.GRAY);
                }
                if (resident[0] == 2)
                {
                    //evil
                    tv.setBackgroundColor(Color.RED);
                    tv.setText("" + resident[1]);
                }
                if (resident[0] == 1)
                {
                    //good
                    tv.setBackgroundColor(Color.GREEN);
                    tv.setText("" + resident[1]);
                }
                if (resident[0] == 0)
                {
                    //player
                    tv.setBackgroundColor(Color.CYAN);
                    tv.setText("" + resident[1]);
                }
                 tr.addView(tv);
            }
            tr.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            mapLayout.addView(tr);

        }

    }

    public int[] determineResidency(List<Entity> inMap, int inX, int inY)
    {
        //[id, strength]
        //id: 0 = player, 1 = good, 2 = evil, 3 = empty
        //strength: 0-100
        int id = 3;
        int str;
        for (Entity e : inMap)
        {
            if (e.getxPos() == inX && e.getyPos() == inY)
            {
                //occupied:
                str = e.getStrength();
                if (!e.isEvil())
                {
                    id = 1;
                }
                if (e.isEvil())
                {
                    id = 2;
                }
                if (e.isPlayer())
                {
                    id = 0;
                    System.out.println("set player entity to zero!");
                }
                int[] result = {id, str};
                return result;
            }
        }
        //not occupied:
        id = 3;
        str = 0;
        int[] result = {id, str};
        return result;
    }

    public void onReturnClick(View view) {
        AntWorldApp mainApp = (AntWorldApp) getApplicationContext();
        Intent intent = new Intent(this, ColonyActivity.class);
        startActivity(intent);
    }

}