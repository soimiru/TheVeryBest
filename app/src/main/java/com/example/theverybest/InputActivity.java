package com.example.theverybest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class InputActivity extends AppCompatActivity {

    private TextView tvQuestions, tvScore;
    private Button submitBtn;
    private TextInputEditText inputTxt;
    boolean answered = false;

    private int score;
    private int totalCorrect;
    private int totalIncorrect;
    private int counter;
    private int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //Asociamos los botones, views y todo lo demás a sus IDs
        submitBtn = findViewById(R.id.submitBtn);
        inputTxt = findViewById(R.id.inputText);
        tvQuestions = findViewById(R.id.TQuestionNumberInput);
        tvScore = findViewById(R.id.TPointsInput);

        //Capturamos las variables del intent
        score = getIntent().getIntExtra("TotalPoints", 0);
        totalCorrect = getIntent().getIntExtra("TotalCorrect", 0);
        totalIncorrect = getIntent().getIntExtra("TotalIncorrect", 0);
        counter = getIntent().getIntExtra("Counter", 0);
        totalQuestions = getIntent().getIntExtra("TotalQuestions", 0);


        //Seteamos los textos
        tvQuestions.setText("Question: "+counter+"/"+(totalQuestions+2));
        tvScore.setText("Score: " +score);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si aun no se ha respondido la pregunta al pulsar el botón de Submit
                if (answered == false){
                    String respuesta = inputTxt.getText().toString();
                    respuesta.toLowerCase();
                    answered = true;
                    counter++;
                    if (respuesta.equals("Gyarados")||respuesta.equals("gyarados")){
                        //Si aciertas
                        Toast.makeText(InputActivity.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                        score +=10;
                        totalCorrect++;
                        inputTxt.setTextColor(Color.GREEN);
                    }else {
                        Toast.makeText(InputActivity.this, "WRONG ANSWER", Toast.LENGTH_SHORT).show();
                        score-=5;
                        totalIncorrect++;
                        inputTxt.setText("Gyarados");
                        inputTxt.setTextColor(Color.RED);
                    }
                    tvScore.setText("Score: " + score);
                    tvQuestions.setText("Question: "+counter+"/"+(totalQuestions+2));
                    submitBtn.setText("Next");
                }else{
                    //Si se había pulsado ya, entonces pasa a la siguiente actividad
                    nextActivity();
                }
            }
        });


    }
    private void nextActivity(){
        //Crea el intento y le pasa las variables
        Intent intent = new Intent(InputActivity.this, FinishActivity.class);
        intent.putExtra("TotalPoints" ,score);
        intent.putExtra("TotalCorrect", totalCorrect);
        intent.putExtra("TotalIncorrect", totalIncorrect);
        intent.putExtra("TotalQuestions", totalQuestions);
        intent.putExtra("Counter", counter);
        startActivity(intent);
    }
}