package com.example.theverybest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button StartButton, CreditsButton;
    private CheckBox HardMode;
    private Spinner NumberQuestions;
    private ArrayList<Integer> TotalQuestions = new ArrayList<Integer>();
    private int NumberQuestionsSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MAIN CODE
        //Inicializamos el Start Button
        StartButton = findViewById(R.id.StartButton);
        CreditsButton = findViewById(R.id.CreditsButton);
        HardMode = findViewById(R.id.Hardmode);
        NumberQuestions = findViewById(R.id.numberQuestions);
        TotalQuestions.add(5);
        TotalQuestions.add(10);
        NumberQuestions.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, TotalQuestions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumberQuestions.setPrompt("Select the number of questions");
        NumberQuestions.setAdapter(adapter);


        //Comportamiento Start Button
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamamos a la Activity Test
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("Hardmode", HardMode.isChecked());
                intent.putExtra("NumberQuestions", NumberQuestionsSelected);
                startActivity(intent);
            }
        });
        CreditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberQuestionsSelected = Integer.parseInt(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}