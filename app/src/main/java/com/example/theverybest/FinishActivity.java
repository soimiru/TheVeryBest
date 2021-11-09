package com.example.theverybest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {
    private TextView tvRes, tvFinalScore, tvCorrect, tvIncorrect, tvTime;
    private Button btnFinish;
    private int score, totalCorrect, totalIncorrect, counter, totalQuestions;
    private Long totalTime;


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

        int seconds = (int) (totalTime / 1000); //Transformamos los ms a segundos y minutos
        int minutes = seconds/60;
        seconds = seconds%60;
        //String time = minutes+":"+seconds;
        String time = String.format("%d:%02d", minutes ,seconds);


        //RANKING
        registrarResultados(time);


        //Asociamos Ids con botones y textos
        tvRes = findViewById(R.id.tvRes);
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvIncorrect = findViewById(R.id.tvIncorrect);
        btnFinish = findViewById(R.id.btnFin);
        tvTime = findViewById(R.id.tvTime);



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

    private void registrarResultados(String time) {
        int player = GamePreferences.playerIDPreferences;
        String playername = GamePreferences.nicknamePreferences;
        int playeravatar = GamePreferences.avatarIDPreferences;

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, Utilities.RANKING_BD, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.RANKING_playerid, player);
        values.put(Utilities.RANKING_playerid, player);
        values.put(Utilities.RANKING_playername, playername);
        values.put(Utilities.RANKING_playeravatar, playeravatar);
        values.put(Utilities.RANKING_score, score);
        values.put(Utilities.RANKING_right, totalCorrect);
        values.put(Utilities.RANKING_wrong, totalIncorrect);
        values.put(Utilities.RANKING_time, time);
        values.put(Utilities.RANKING_totaltime, totalTime);

        Long idResult = db.insert(Utilities.RANKING_BD, Utilities.RANKING_id, values);
        if(idResult != 1){
            Toast.makeText(this, "New score was registered.", Toast.LENGTH_SHORT).show();
        }
        System.out.println(db.rawQuery("SELECT COUNT(*) FROM "+Utilities.RANKING_BD, null) + "");
        db.close();
    }
}