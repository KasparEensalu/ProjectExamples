package com.example.kaspar.funflags;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.kaspar.funflags.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Settings extends ActionBarActivity {

    private Properties Props = new Properties();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Props.setProperty("worldPart", "");
        Props.setProperty("flagTime", "");
        Props.setProperty("flagSize", "");

        final Button save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = saveSettings();
                if (b) {
                    finish();
                }
            }

        });

        final CheckBox asia_game = (CheckBox) findViewById(R.id.check_asia);
        final CheckBox africa_game = (CheckBox) findViewById(R.id.check_africa);
        final CheckBox america_game = (CheckBox) findViewById(R.id.check_america);
        final CheckBox europe_game = (CheckBox) findViewById(R.id.check_europe);

        final CheckBox half_second = (CheckBox) findViewById(R.id.check_0_5);
        final CheckBox one_second = (CheckBox) findViewById(R.id.check_1);
        final CheckBox two_second = (CheckBox) findViewById(R.id.check_2);

        final CheckBox size100 = (CheckBox) findViewById(R.id.check_100);
        final CheckBox size75 = (CheckBox) findViewById(R.id.check_75);
        final CheckBox size150 = (CheckBox) findViewById(R.id.check_150);

        HashMap<String, String> settings = setCurrentSettings();
        int time = Integer.parseInt(settings.get("time"));
        String part = settings.get("part");
        int size = Integer.parseInt(settings.get("size"));

        if (part.equals("Europe")){
            europe_game.setChecked(true);
            Props.setProperty("worldPart", "1");
        } else if (part.equals("Africa")) {
            africa_game.setChecked(true);
            Props.setProperty("worldPart", "2");
        } else if (part.equals("Asia")) {
            asia_game.setChecked(true);
            Props.setProperty("worldPart", "3");
        } else {
            america_game.setChecked(true);
            Props.setProperty("worldPart", "4");
        }

        if (time == 500) {
            half_second.setChecked(true);
            Props.setProperty("flagTime", "500");
        } else if (time == 1000) {
            one_second.setChecked(true);
            Props.setProperty("flagTime", "1000");
        } else {
            two_second.setChecked(true);
            Props.setProperty("flagTime", "2000");
        }

        if (size == 75) {
            size75.setChecked(true);
            Props.setProperty("flagSize", "75");
        } else if (size == 100) {
            size100.setChecked(true);
            Props.setProperty("flagSize", "100");
        } else {
            size150.setChecked(true);
            Props.setProperty("flagSize", "200");
        }


        asia_game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                africa_game.setChecked(false);
                america_game.setChecked(false);
                europe_game.setChecked(false);
                asia_game.setChecked(b);
                if(b == true) {
                    updateSettings("worldPart", "3");
                }
            }
        });

        africa_game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                asia_game.setChecked(false);
                america_game.setChecked(false);
                europe_game.setChecked(false);
                africa_game.setChecked(b);
                if(b == true) {
                    updateSettings("worldPart", "2");
                }
            }
        });

        america_game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                africa_game.setChecked(false);
                asia_game.setChecked(false);
                europe_game.setChecked(false);
                america_game.setChecked(b);
                if(b == true) {
                    updateSettings("worldPart", "4");
                }
            }
        });

        europe_game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                africa_game.setChecked(false);
                america_game.setChecked(false);
                asia_game.setChecked(false);
                europe_game.setChecked(b);
                if(b == true) {
                    updateSettings("worldPart", "1");
                }

            }
        });

        half_second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                one_second.setChecked(false);
                two_second.setChecked(false);
                half_second.setChecked(b);
                if(b == true) {
                    updateSettings("flagTime", "500");
                }
            }
        });

        one_second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                half_second.setChecked(false);
                two_second.setChecked(false);
                one_second.setChecked(b);
                if(b == true) {
                    updateSettings("flagTime", "1000");
                }
            }
        });

        two_second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                one_second.setChecked(false);
                half_second.setChecked(false);
                two_second.setChecked(b);
                if(b == true) {
                    updateSettings("flagTime", "2000");
                }
            }
        });

        size100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                size150.setChecked(false);
                size75.setChecked(false);
                size100.setChecked(b);
                if(b == true) {
                    updateSettings("flagSize", "100");
                }
            }
        });

        size150.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                size100.setChecked(false);
                size75.setChecked(false);
                size150.setChecked(b);
                if(b == true) {
                    updateSettings("flagSize", "150");
                }
            }
        });

        size75.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                size150.setChecked(false);
                size100.setChecked(false);
                size75.setChecked(b);
                if(b == true) {
                    updateSettings("flagSize", "75");
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void updateSettings(String setting, String update) {
            Props.setProperty(setting, update);
    }

    public boolean saveSettings() {
        try {
            if (Props.getProperty("worldPart").equals("")
                    || Props.getProperty("flagTime").equals("")
                    || Props.getProperty("flagSize").equals("")) {
                return false;
            }

            FileOutputStream fos = openFileOutput("settings", Context.MODE_PRIVATE);

            Props.store(fos, "Stored");
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public HashMap<String, String> setCurrentSettings() {
        try {
            Properties prop = new Properties();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("settings")));
            if (inputReader != null) {
                prop.load(inputReader);
            } else {

                throw new FileNotFoundException("property file not found in the classpath");

            }
            HashMap<String, String> settings = new HashMap<String, String>();
            String worldPart = prop.getProperty("worldPart").toString();
            int partId = Integer.parseInt(worldPart);
            if (partId == 1) {
                settings.put("part", "Europe");
            } else if (partId == 2) {
                settings.put("part", "Africa");
            } else if (partId == 3) {
                settings.put("part", "Asia");
            } else {
                settings.put("part", "America");
            }

            String flagtime = prop.getProperty("flagTime").toString();
            int time = Integer.parseInt(flagtime);
            if (time == 500) {
                settings.put("time", "500");
            } else if (time == 1000) {
                settings.put("time", "1000");
            } else {
                settings.put("time", "2000");
            }

            String flagSize = prop.getProperty("flagSize").toString();
            int size = Integer.parseInt(flagSize);
            if (size == 75) {
                settings.put("size", "75");
            } else if (size == 100) {
                settings.put("size", "100");
            } else {
                settings.put("size", "200");
            }
            return settings;

        } catch (Exception e) {
            return null;
        }

    }
}
