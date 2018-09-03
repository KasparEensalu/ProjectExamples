package com.example.kaspar.funflags;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Properties;


public class Highscores extends ActionBarActivity {

    private TextView worldpart;

    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView name4;
    private TextView name5;
    private TextView name6;
    private TextView name7;
    private TextView name8;
    private TextView name9;
    private TextView name10;

    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private TextView score5;
    private TextView score6;
    private TextView score7;
    private TextView score8;
    private TextView score9;
    private TextView score10;

    private String worldPart;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        worldpart = (TextView) findViewById(R.id.world_part);
        name1 = (TextView) findViewById(R.id.name_1);
        name2 = (TextView) findViewById(R.id.name_2);
        name3 = (TextView) findViewById(R.id.name_3);
        name4 = (TextView) findViewById(R.id.name_4);
        name5 = (TextView) findViewById(R.id.name_5);
        name6 = (TextView) findViewById(R.id.name_6);
        name7 = (TextView) findViewById(R.id.name_7);
        name8 = (TextView) findViewById(R.id.name_8);
        name9 = (TextView) findViewById(R.id.name_9);
        name10 = (TextView) findViewById(R.id.name_10);

        score1 = (TextView) findViewById(R.id.score_1);
        score2 = (TextView) findViewById(R.id.score_2);
        score3 = (TextView) findViewById(R.id.score_3);
        score4 = (TextView) findViewById(R.id.score_4);
        score5 = (TextView) findViewById(R.id.score_5);
        score6 = (TextView) findViewById(R.id.score_6);
        score7 = (TextView) findViewById(R.id.score_7);
        score8 = (TextView) findViewById(R.id.score_8);
        score9 = (TextView) findViewById(R.id.score_9);
        score10 = (TextView) findViewById(R.id.score_10);

        String worldPartid = getWorldPart();
        if (worldPartid.equals("1")) {
            worldPart = "Europe";
        } else if (worldPartid.equals("2")) {
            worldPart = "Africa";
        } else if (worldPartid.equals("3")) {
            worldPart = "Asia";
        } else {
            worldPart = "America";
        }
        worldpart.setText(worldPart);
        setHighscores();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getWorldPart() {
        try {
            Properties prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("settings")));
            prop.load(inputReader);
            return(prop.getProperty("worldPart"));


        } catch (Exception e) {
            return null;
        }
    }

    public void setHighscores() {
        try {
            String filename = worldPart.toLowerCase()+"Highscores";
            Properties prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            prop.load(inputReader);

            name1.setText(prop.getProperty("name1"));
            name2.setText(prop.getProperty("name2"));
            name3.setText(prop.getProperty("name3"));
            name4.setText(prop.getProperty("name4"));
            name5.setText(prop.getProperty("name5"));
            name6.setText(prop.getProperty("name6"));
            name7.setText(prop.getProperty("name7"));
            name8.setText(prop.getProperty("name8"));
            name9.setText(prop.getProperty("name9"));
            name10.setText(prop.getProperty("name10"));

            score1.setText(prop.getProperty("score1"));
            score2.setText(prop.getProperty("score2"));
            score3.setText(prop.getProperty("score3"));
            score4.setText(prop.getProperty("score4"));
            score5.setText(prop.getProperty("score5"));
            score6.setText(prop.getProperty("score6"));
            score7.setText(prop.getProperty("score7"));
            score8.setText(prop.getProperty("score8"));
            score9.setText(prop.getProperty("score9"));
            score10.setText(prop.getProperty("score10"));





        } catch (Exception e) {

        }

    }
}
