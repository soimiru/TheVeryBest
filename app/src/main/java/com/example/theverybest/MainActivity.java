package com.example.theverybest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button musicButton;
    private ImageButton ImageStart, ImageSettings;
    private ArrayList<Integer> TotalQuestions = new ArrayList<Integer>();
    private int NumberQuestionsSelected, sonidoRep;
    SoundPool sp;
    MediaPlayer snoverSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MAIN CODE
        //Inicializamos el Start Button
        musicButton = findViewById(R.id.buttonMusic);
        //sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        //sonidoRep = sp.load(this, R.raw.snoversound, 1);
        ImageStart = findViewById(R.id.imagePlay);
        ImageSettings = findViewById(R.id.imageSettings);

        TotalQuestions.add(5);
        TotalQuestions.add(10);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, TotalQuestions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snoverSound = MediaPlayer.create(MainActivity.this, R.raw.snoversound);

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snoverSound.start();
            }
        });

        //Comportamiento Start Button
        ImageStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamamos a la Activity Test
                /*Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("Hardmode", HardMode.isChecked());
                intent.putExtra("NumberQuestions", NumberQuestionsSelected);
                startActivity(intent);*/

                //Preferences
                PreferenceManager.setDefaultValues(MainActivity.this, R.xml.preferences, false);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                GamePreferences.getPreferences(preferences, MainActivity.this);

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("Hardmode", GamePreferences.gM);
                intent.putExtra("NumberQuestions", GamePreferences.nQ);
                startActivity(intent);
            }
        });


        ImageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfiguration();
            }
        });



    }

    public void goToConfiguration(){
        Intent intent = new Intent(this, Configuration.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberQuestionsSelected = Integer.parseInt(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}