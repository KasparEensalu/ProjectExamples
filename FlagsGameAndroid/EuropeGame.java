package com.example.kaspar.funflags;

import com.example.kaspar.funflags.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class EuropeGame extends Activity {


    private RelativeLayout gameBoard;

    private int width;
    private int height;

    private TextView score;
    private TextView time;
    private TextView bonus;
    private TextView highscore;
    private TextView Region;

    private int worldPart;
    private int flagTime;
    private int flagSize;
    private int timeCounter;
    private int scoreCount;

    private boolean killthread = false;

    private String region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_europe_game);

        gameBoard = (RelativeLayout) findViewById(R.id.game_board);
        score = (TextView) findViewById(R.id.score);
        time = (TextView) findViewById(R.id.timer);
        bonus = (TextView) findViewById(R.id.bonus);
        Region = (TextView) findViewById(R.id.world_part);


        this.scoreCount = 0;
        this.worldPart = Integer.parseInt(getSetting("worldPart"));
        this.flagTime = Integer.parseInt(getSetting("flagTime"));
        this.flagSize = Integer.parseInt(getSetting("flagSize"));
        this.timeCounter = 0;
        bonus.setText("");
        bonus.setY(0);
        time.setText("61");
        score.setText("Score: 0");


        if (worldPart == 1) {
            region = "Europe";
        } else if (worldPart == 2) {
            region = "Africa";
        } else if (worldPart == 3) {
            region = "Asia";
        } else {
            region = "America";
        }

        Region.setText("Tap flags of " + region);
        Region.setY(flagSize);
        highscore = (TextView) findViewById(R.id.score_to_beat);
        highscore.setText("Score to beat: " + getScoreToBeat());
        startGame();
        if (this.isFinishing()) {
            killthread = true;

        }

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.height = gameBoard.getHeight();
        this.width = gameBoard.getWidth();

    }

    @Override
    public void onBackPressed(){
        killthread = true;
        this.finish();
        return;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.width = gameBoard.getHeight();
        this.height = gameBoard.getWidth();
        bonus.setY(flagSize);
    }


    private void addEuropeFlag() {
        int x = randomInt(width - flagSize);
        int y = 100 + randomInt(height - flagSize - 100);
        if (Evaluate(x, y) == false) {
            addEuropeFlag();
        } else {
            final EuropeFlag button = new EuropeFlag(this, null);
            button.size = flagSize;
            int timeToDisappear = ((flagTime*15) /(flagSize/50))*2-200;
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameBoard.removeView(button);
                    }
                }, timeToDisappear);

            final int buttonId = 1;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ViewPropertyAnimator.animate(button).x(0).y(0).setDuration(500).start();
                    button.setClickable(false);
                    button.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gameBoard.removeView(button);
                        }
                    }, 500);

                    bonus = (TextView) findViewById(R.id.bonus);
                    score = (TextView) findViewById(R.id.score);
                    if (buttonId == worldPart) {
                        bonus.setText("+1");
                        bonus.setTextColor(Color.GREEN);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) + 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount++;
                    } else {
                        bonus.setText("-1");
                        bonus.setTextColor(Color.RED);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) - 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount--;
                    }

                }
            });

            button.setX(x);
            button.setY(y);
            gameBoard.addView(button);
        }

    }

    private void addAfricaFlag() {
        int x = randomInt(width - flagSize);
        int y = 100 + randomInt(height - flagSize - 100);
        if (Evaluate(x, y) == false) {
            addAfricaFlag();
        } else {
            final AfricaFlag button = new AfricaFlag(this, null);
            final int buttonId = 2;
            button.size = flagSize;
            int timeToDisappear = ((flagTime*15) /(flagSize/50))*2;
            button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameBoard.removeView(button);
                }
            }, timeToDisappear);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPropertyAnimator.animate(button).x(0).y(0).setDuration(500).start();
                    button.setClickable(false);
                    button.postDelayed(new Runnable() {
                        @Override

                        public void run() {
                            gameBoard.removeView(button);
                        }
                    }, 500);
                    bonus = (TextView) findViewById(R.id.bonus);
                    score = (TextView) findViewById(R.id.score);
                    if (buttonId == worldPart) {
                        bonus.setText("+1");
                        bonus.setTextColor(Color.GREEN);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) + 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount++;
                    } else {
                        bonus.setText("-1");
                        bonus.setTextColor(Color.RED);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) - 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount--;
                    }

                }
            });

            button.setX(x);
            button.setY(y);
            gameBoard.addView(button);

        }
    }

    private void addAmericaFlag() {
        int x = randomInt(width - flagSize);
        int y = 100 + randomInt(height - flagSize - 100);
        if (Evaluate(x, y) == false) {
            addAmericaFlag();
        } else {
            final AmericaFlag button = new AmericaFlag(this, null);
            final int buttonId = 4;
            button.size = flagSize;
            int timeToDisappear = ((flagTime*15) /(flagSize/50))*2;
            button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameBoard.removeView(button);
                }
            }, timeToDisappear);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPropertyAnimator.animate(button).x(0).y(0).setDuration(500).start();
                    button.setClickable(false);
                    button.postDelayed(new Runnable() {
                        @Override

                        public void run() {
                            gameBoard.removeView(button);
                        }
                    }, 500);
                    bonus = (TextView) findViewById(R.id.bonus);
                    score = (TextView) findViewById(R.id.score);
                    if (buttonId == worldPart) {
                        bonus.setText("+1");
                        bonus.setTextColor(Color.GREEN);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) + 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount++;
                    } else {
                        bonus.setText("-1");
                        bonus.setTextColor(Color.RED);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) - 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount--;
                    }

                }
            });

            button.setX(x);
            button.setY(y);
            gameBoard.addView(button);

        }
    }

    private void addAsiaFlag() {
        int x = randomInt(width - flagSize);
        int y = 100 + randomInt(height - flagSize - 100);
        if (Evaluate(x, y) == false) {
            addAsiaFlag();
        } else {
            final AsiaFlag button = new AsiaFlag(this, null);
            final int buttonId = 3;
            button.size = flagSize;
            int timeToDisappear = ((flagTime*15) /(flagSize/50))*2;
            button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameBoard.removeView(button);
                }
            }, timeToDisappear);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPropertyAnimator.animate(button).x(0).y(0).setDuration(500).start();
                    button.setClickable(false);
                    button.postDelayed(new Runnable() {
                        @Override

                        public void run() {
                            gameBoard.removeView(button);
                        }
                    }, 500);
                    bonus = (TextView) findViewById(R.id.bonus);
                    score = (TextView) findViewById(R.id.score);
                    if (buttonId == worldPart) {
                        bonus.setText("+1");
                        bonus.setTextColor(Color.GREEN);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) + 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount++;
                    } else {
                        bonus.setText("-1");
                        bonus.setTextColor(Color.RED);
                        String[] scorepts = ((String) score.getText()).split(" ");
                        int newScore = Integer.parseInt(scorepts[1]) - 1;
                        score.setText("score: " + Integer.toString(newScore));
                        scoreCount--;
                    }

                }
            });

            button.setX(x);
            button.setY(y);
            gameBoard.addView(button);

        }
    }

    private boolean timeSetter() {

        if (this.flagTime == 500) {
            if (this.timeCounter == 0){
                this.timeCounter = 1;

                return true;
            } else {
                this.timeCounter = 0;
                TextView time = (TextView) findViewById(R.id.timer);
                String timeToSet = Integer.toString(Integer.parseInt((String)time.getText()) - 1);
                time.setText(timeToSet);
                if (Integer.parseInt(timeToSet) < 10) {
                    time.setTextColor(Color.RED);
                }

                if (Integer.parseInt((String)time.getText()) == 0) {
                    killthread = true;
                    endGame();
                    this.finish();
                }
                return true;
            }
        } else if (this.flagTime == 2000) {
            TextView time = (TextView) findViewById(R.id.timer);
            String timeToSet = Integer.toString(Integer.parseInt((String)time.getText()) - 1);
            time.setText(timeToSet);
            if (Integer.parseInt(timeToSet) < 10) {
                time.setTextColor(Color.RED);
            }
            time.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TextView time = (TextView) findViewById(R.id.timer);
                    String timeToSet = Integer.toString(Integer.parseInt((String)time.getText()) - 1);
                    time.setText(timeToSet);
                    if (Integer.parseInt(timeToSet) < 10) {
                        time.setTextColor(Color.RED);
                    }

                }
            }, 1000);
            if (Integer.parseInt((String)time.getText()) == 0) {
                killthread = true;
                endGame();
                this.finish();
            }
            return true;
        }

        TextView time = (TextView) findViewById(R.id.timer);
        String timeToSet = Integer.toString(Integer.parseInt((String) time.getText()) - 1);
        time.setText(timeToSet);
        if (Integer.parseInt(timeToSet) < 10) {
            time.setTextColor(Color.RED);
        }
        killthread = false;
        if (Integer.parseInt((String) time.getText()) == 0) {
            killthread = true;
            endGame();
            this.finish();

        }
        return true;
    }



    private String getSetting(String setting) {
        try {
            Properties prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("settings")));
            if (inputReader != null) {
                prop.load(inputReader);
            } else {

                throw new FileNotFoundException("property file not found in the classpath");

            }
            if (setting.equals("worldPart")) {

                return(prop.getProperty("worldPart"));
            } else if (setting.equals("flagTime")) {
                return(prop.getProperty("flagTime"));
            } else if (setting.equals("flagSize")) {
                return (prop.getProperty("flagSize"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private int randomInt(int max) {
        return new Random().nextInt(max + 1);
    }

    private boolean Evaluate(int x, int y) {

        ArrayList<View> children = new ArrayList<View>();
        int childcount = 0;
        while (childcount < gameBoard.getChildCount()){
            View added = gameBoard.getChildAt(childcount);
            children.add(added);
            childcount++;
        }
        for (View button : children){
            if (button instanceof Button){
                int distance = (int)(Math.sqrt( ((button.getX()-x)*(button.getX()-x))+( (button.getY()-y)*(button.getY()-y) ) ));
                if (distance < flagSize/4*3) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getScoreToBeat() {
        try {
            String filename = region.toLowerCase() + "Highscores";
            Properties prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            prop.load(inputReader);

            return prop.getProperty("score1");

        } catch (Exception e){
            return "Oh God :/";
        }
    }

    private void startGame() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {

                    while (true){
                        int time = flagTime;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeSetter();
                            }
                        });


                        Thread.sleep(time);
                        if(killthread) {
                            break;
                        } else {
                            int rnd = randomInt(4);
                            if (rnd <= 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (worldPart == 1) {
                                            addEuropeFlag();
                                        }else if (worldPart == 2) {
                                            addAfricaFlag();
                                        }else if (worldPart == 3) {
                                            addAsiaFlag();
                                        }else {
                                            addAmericaFlag();
                                        }
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int rnd = randomInt(5);
                                        if( rnd == 1) {
                                            addEuropeFlag();
                                        } else if (rnd == 2) {
                                            addAfricaFlag();
                                        }else if (rnd == 3) {
                                            addAsiaFlag();
                                        } else {
                                            addAmericaFlag();
                                        }

                                    }
                                });
                            }
                        }
                    }
                } catch (InterruptedException e) {}
            }
        };
        t.start();


    }

    public void endGame() {
        Intent intent = new Intent(this, GameEnd.class);
        intent.putExtra("region", region);
        try {
            Properties Props = new Properties();
            FileOutputStream fos = openFileOutput("lastScore", Context.MODE_PRIVATE);

            Props.put("score", Integer.toString(scoreCount));
            Props.store(fos, "Stored");
        }  catch ( Exception e){

        }
        startActivity(intent);
    }
}
