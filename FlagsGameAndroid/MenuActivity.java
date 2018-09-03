package com.example.kaspar.funflags;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.util.Properties;


public class MenuActivity extends ActionBarActivity {

    private Button settingsButton;
    private Button highscoresButton;
    private Button playButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settingsButton = (Button) findViewById(R.id.settings_button);

        Thread cleanupthread = new Thread();
        cleanupthread.start();


        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSettings();
            }
        });

        playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });

        resetButton = (Button) findViewById(R.id.reset_highscores);

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighscores();
            }
        });

        highscoresButton = (Button) findViewById(R.id.highscores_button);

        highscoresButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startHighscores();
            }
        });
    }

    public void startSettings() {
        Intent intentSettings = new Intent(this,Settings.class);
        startActivity(intentSettings);
    }


    public void startGame(){
        Intent intent = new Intent(this,EuropeGame.class );
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    public void resetHighscores() {

        try {
            Properties Props = new Properties();
            int count = 1;
            while(count < 11) {
                String name = "name"+Integer.toString(count);
                String namer ="Nameless " + Integer.toString(count);
                Props.put(name, namer);

                String score = "score"+Integer.toString(count);
                String scorer = "0";
                Props.put(score, scorer);
                count++;
            }



            FileOutputStream fos = openFileOutput("europeHighscores", Context.MODE_PRIVATE);
            Props.store(fos, "Stored");
            fos.close();
            FileOutputStream fos2 = openFileOutput("africaHighscores", Context.MODE_PRIVATE);
            Props.store(fos2, "Stored");
            fos2.close();
            FileOutputStream fos3 = openFileOutput("asiaHighscores", Context.MODE_PRIVATE);
            Props.store(fos3, "Stored");
            fos3.close();
            FileOutputStream fos4 = openFileOutput("americaHighscores", Context.MODE_PRIVATE);
            Props.store(fos4, "Stored");
            fos4.close();



        } catch (Exception e) {

        }
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

    public void startHighscores() {
        Intent intent = new Intent(this, Highscores.class );
        startActivity(intent);
    }
}
