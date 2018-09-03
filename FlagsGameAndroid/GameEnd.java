package com.example.kaspar.funflags;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kaspar.funflags.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class GameEnd extends ActionBarActivity {

    private String region;
    private int score;
    private int myRank;

    private TextView congratulations;
    private TextView rank;
    private TextView regionView;
    private EditText nameField;

    private Properties prop;

    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        congratulations = (TextView) findViewById(R.id.congratulations);
        rank = (TextView) findViewById(R.id.ranking);
        regionView = (TextView) findViewById(R.id.regionView);
        nameField = (EditText) findViewById(R.id.nameField);

        this.score = getLastScore();
        regionView.setText(Integer.toString(this.score));



        region = getIntent().getStringExtra("region");

        if(isEligible()) {
            congratulations.setText("Congratulations! You ranked :");
            regionView.setText("in " + region);
            myRank = getRank();
            rank.setText(Integer.toString(myRank) + ".");
            nameField.setEnabled(true);
            nameField.setText("Type your name here");
        } else {
            congratulations.setText("Unfortunately, you are bad.");
            rank.setText(":(");
            regionView.setText("");
            nameField.setEnabled(false);
        }

        menuButton = (Button) findViewById(R.id.menu_button);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameField.isEnabled()) {
                    if (nameField.getText().toString().equals("Type your name here") || nameField.getText().toString().equals("")
                            || nameField.getText().toString().equals("Please enter your name!")){
                        nameField.setText("Please enter your name!");
                    } else {
                        saveHighscore();
                        toMainMenu();
                    }
                } else {
                    toMainMenu();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_end, menu);
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

    private int getRank() {

        try {

            String filename = region.toLowerCase() + "Highscores";
            int scorepls = this.getScore();


            prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            prop.load(inputReader);

            int counter = 9;
            int rankint = 0;

            while (counter > 0) {


                int scorecurrent = Integer.parseInt(prop.getProperty("score" + Integer.toString(counter)));


                if (counter == 9) {
                    if (scorepls < scorecurrent) {
                        rankint = 10;
                        break;
                    } else {
                        counter--;

                    }
                } else if (counter == 1) {
                    rankint = 1;
                    break;
                } else if (scorepls >= scorecurrent && scorepls < Integer.parseInt(prop.getProperty("score" + Integer.toString(counter-1)))) {
                    rankint = counter;
                    break;
                } else if (scorepls >= scorecurrent && scorepls >= Integer.parseInt(prop.getProperty("score" + Integer.toString(counter-1)))) {
                    counter--;

                }
            }
            return rankint;

        } catch (Exception e) {
            return 97;
        }

    }


    private boolean isEligible() {
        try {
            String filename = region.toLowerCase() + "Highscores";
            prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            prop.load(inputReader);
            inputReader.close();

            if (Integer.parseInt(prop.getProperty("score10")) > score ) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    private void toMainMenu() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        this.finish();



    }

    private int getScore() {
        return this.score;
    }

    private int getLastScore() {
        try {
            prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("lastScore")));
            prop.load(inputReader);
            return(Integer.parseInt(prop.getProperty("score")));


        } catch (Exception e) {
            return 0;
        }


    }

    private void saveHighscore() {
        try {
            String filename = region.toLowerCase() + "Highscores";
            Properties props = new Properties();
            Properties newProp = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            props.load(inputReader);

            newProp.load(inputReader);
            inputReader.close();

            int counter = myRank;
            while (counter <= 10) {

                if (counter == myRank) {
                    newProp.put("score" + Integer.toString(counter), Integer.toString(score));
                    String name = nameField.getText().toString();
                    newProp.put("name" + Integer.toString(counter), name);
                    counter++;
                } else {
                    String score = props.getProperty("score" + Integer.toString(counter - 1));

                    newProp.put("score" + Integer.toString(counter), score);
                    String name = props.getProperty("name" + Integer.toString(counter-1));

                    newProp.put("name" + Integer.toString(counter), name);
                    counter++;
                }
            }
            counter = myRank - 1;

            while (counter > 0) {
                newProp.put("score" + Integer.toString(counter), props.getProperty("score" + Integer.toString(counter)));
                newProp.put("name" + Integer.toString(counter), props.getProperty("name" + Integer.toString(counter)));
                counter--;
            }

            FileOutputStream fos3 = openFileOutput(filename, Context.MODE_PRIVATE);

            newProp.store(fos3, "Stored");

            fos3.close();



        } catch (Exception e) {

        }
    }
}
