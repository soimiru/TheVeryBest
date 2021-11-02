package com.example.theverybest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ImagesActivity extends AppCompatActivity {


    private ImageButton img1, img2, img3, img4;
    private TextView  tvOpt1, tvOpt2, tvOpt3, tvOpt4, tvQuestions, tvScore;
    private boolean answered = false;

    private int score;
    private int totalCorrect;
    private int totalIncorrect;
    private int counter;
    private int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        score = getIntent().getIntExtra("TotalPoints", 0);
        totalCorrect = getIntent().getIntExtra("TotalCorrect", 0);
        totalIncorrect = getIntent().getIntExtra("TotalIncorrect", 0);
        counter = getIntent().getIntExtra("Counter", 0);
        totalQuestions = getIntent().getIntExtra("TotalQuestions", 0);

        img1 = findViewById(R.id.imgBtn1);
        img2 = findViewById(R.id.imgBtn2);
        img3 = findViewById(R.id.imgBtn3);
        img4 = findViewById(R.id.imgBtn4);

        tvOpt1 = findViewById(R.id.tvOpt1);
        tvOpt2 = findViewById(R.id.tvOpt2);
        tvOpt3 = findViewById(R.id.tvOpt3);
        tvOpt4 = findViewById(R.id.tvOpt4);



        tvQuestions = findViewById(R.id.TQuestionNumberImg);
        tvScore = findViewById(R.id.TPointsImg);

        tvQuestions.setText("Question: "+counter+"/"+(totalQuestions+2));
        tvScore.setText("Score: " +score);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false){
                    boolean answer = false;
                    checkAnswer(answer);
                }else{
                    nextActivity();
                }

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false){
                    boolean answer = false;
                    checkAnswer(answer);
                }else{
                    nextActivity();
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false){
                    boolean answer = false;
                    checkAnswer(answer);
                }else{
                    nextActivity();
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false){
                    boolean answer = true;
                    checkAnswer(answer);
                }else{
                    nextActivity();
                }
            }
        });

    }

    private void checkAnswer(boolean answer){
        answered = true;
        counter++;
        if (answer == true){
            score+=10;
            totalCorrect++;
        }else{
            score-=5;
            totalIncorrect++;
        }
        tvScore.setText("Score: " + score);
        tvQuestions.setText("Question: "+counter+"/"+(totalQuestions+2));
        tvOpt1.setTextColor(Color.RED);
        tvOpt2.setTextColor(Color.RED);
        tvOpt3.setTextColor(Color.RED);
        tvOpt4.setTextColor(Color.GREEN);
        Toast.makeText(ImagesActivity.this, "Click again to go to next question", Toast.LENGTH_SHORT).show();
    }

    private void nextActivity(){
        Intent intent = new Intent(ImagesActivity.this, InputActivity.class);
        intent.putExtra("TotalPoints" ,score);
        intent.putExtra("TotalCorrect", totalCorrect);
        intent.putExtra("TotalIncorrect", totalIncorrect);
        intent.putExtra("TotalQuestions", totalQuestions);
        intent.putExtra("Counter", counter);
        startActivity(intent);
    }
}