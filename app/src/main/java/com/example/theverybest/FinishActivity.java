package com.example.theverybest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {
    private TextView tvRes, tvFinalScore, tvCorrect, tvIncorrect, tvTime;
    private Button btnFinish;
    private int score, totalCorrect, totalIncorrect, counter, totalQuestions;
    private long totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        //Variables del intent
        score = getIntent().getIntExtra("TotalPoints", 0);
        totalCorrect = getIntent().getIntExtra("TotalCorrect", 0);
        totalIncorrect = getIntent().getIntExtra("TotalIncorrect", 0);
        counter = getIntent().getIntExtra("Counter", 0);
        totalQuestions = getIntent().getIntExtra("TotalQuestions", 0);
        totalTime = getIntent().getLongExtra("time", 0);

        int seconds = (int) (totalTime / 1000);
        int minutes = seconds/60;
        seconds = seconds%60;

        //Asociamos Ids con botones y textos
        tvTime = findViewById(R.id.tvTime);
        tvRes = findViewById(R.id.tvRes);
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvIncorrect = findViewById(R.id.tvIncorrect);
        btnFinish = findViewById(R.id.btnFin);

        if (totalQuestions== 10){
            switch (totalCorrect){
                case 0:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 1:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 2:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 3:
                    tvRes.setText("You are Joven Chano");
                    break;
                case 4:
                    tvRes.setText("You are Joven Chano");
                    break;
                case 5:
                    tvRes.setText("You rock like Onyx");
                    break;
                case 6:
                    tvRes.setText("You rock like Onyx");
                    break;
                case 7:
                    tvRes.setText("You are Francesco Virgolinni");
                    break;
                case 8:
                    tvRes.setText("You are The Champion");
                    break;
                case 9:
                    tvRes.setText("You are The Champion");
                    break;
                case 10:
                    tvRes.setText("You are a Real Pokémon Master");
                    break;
            }

        }else if(totalQuestions == 15){
            switch (totalCorrect){
                case 0:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 1:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 2:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 3:
                    tvRes.setText("You are Joven Chano");
                    break;
                case 4:
                    tvRes.setText("You are Joven Chano");
                    break;
                case 5:
                    tvRes.setText("You rock like Onyx");
                    break;
                case 6:
                    tvRes.setText("You rock like Onyx");
                    break;
                case 7:
                    tvRes.setText("You are a crack");
                    break;
                case 8:
                    tvRes.setText("You are a crack");
                    break;
                case 9:
                    tvRes.setText("You are Francesco Virgolinni");
                    break;
                case 10:
                    tvRes.setText("You are The Champion");
                    break;
                case 11:
                    tvRes.setText("You are The Champion");
                    break;
                case 12:
                    tvRes.setText("You are The Champion");
                    break;
                case 13:
                    tvRes.setText("You are The Champion");
                    break;
                case 14:
                    tvRes.setText("You are a Real Pokémon Master");
                    break;
                case 15:
                    tvRes.setText("You are a Real Pokémon Master");
                    break;


            }
        } else{
            switch (totalCorrect){
                case 0:
                    tvRes.setText("You are a Wurmple");
                    break;
                case 1:
                    tvRes.setText("You are Joven Chano");
                    break;
                case 2:
                    tvRes.setText("You rock like Onyx");
                    break;
                case 3:
                    tvRes.setText("You are Francesco Virgolinni");
                    break;
                case 4:
                    tvRes.setText("You are The Champion");
                    break;
                case 5:
                    tvRes.setText("You are a Real Pokémon Master");
                    break;
            }

        }
        //Seteamos los textos finales
        tvFinalScore.setText(score + " points");
        tvCorrect.setText(totalCorrect + " Corrects");
        tvIncorrect.setText(totalIncorrect+ " Incorrects");
        tvTime.setText(String.format("Total Time: "+"%d:%02d", minutes, seconds));

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}