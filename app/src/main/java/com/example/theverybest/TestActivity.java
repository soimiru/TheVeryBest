package com.example.theverybest;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theverybest.fragments.TestFragment;

import java.util.ArrayList;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    //Declaración de variables para el comportamiento
    int totalQuestions;
    private TextView tvTimer;

    Random r = new Random();
    CountDownTimer countDownTimer;
    ColorStateList dfRbColor;
    boolean answered;


    //BASE DE DATOS
    //private QuestionViewModel questionViewModel;
    private ArrayList<Questions> questionsPool;
    public QuestionViewModel questionViewModel ;
    private Questions currentQuestion;


    long startTime;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds/60;
            seconds = seconds%60;

            tvTimer.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startTime = System.currentTimeMillis();

        setContentView(R.layout.activity_test);
        //MAIN CODE
        //Inicialización de las variables
        Boolean hardmode = getIntent().getBooleanExtra("Hardmode", false);
        totalQuestions = getIntent().getIntExtra("NumberQuestions", 5);
        questionsPool = new ArrayList<>();
        questionsPool = getIntent().getParcelableArrayListExtra("QuestionsPool");
        tvTimer = findViewById(R.id.TTimer);

        TestFragment testFragment = new TestFragment();

        if (savedInstanceState == null) {
            //BUNDLE & FRAGMENT
            Bundle bundle = new Bundle();
            bundle.putInt("totalQuestions", totalQuestions);
            bundle.putParcelableArrayList("questions", questionsPool);
            bundle.putLong("startTime", startTime);
            testFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, testFragment)
                    .commit();
        }

        //Base de datos
        /*
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                if(questionsPool == null){
                    questionsPool = new ArrayList<Questions>();

                    while(questions.size() > 0){
                        questionsPool.add(questions.remove(0));
                    }
                }
            }
        });
        */

        //TIMER

        timerHandler.postDelayed(timerRunnable, 0);
    }


    public ArrayList<Questions> getQuestionsPool(){
        return questionsPool;
    }


}