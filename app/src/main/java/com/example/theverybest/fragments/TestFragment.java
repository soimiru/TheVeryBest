package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.theverybest.FinishActivity;
import com.example.theverybest.GamePreferences;
import com.example.theverybest.Questions;
import com.example.theverybest.R;
import com.example.theverybest.TestActivity;

import java.util.ArrayList;
import java.util.Random;

public class TestFragment extends Fragment {

    private ArrayList<Questions> questionsList = new ArrayList<Questions>();
    private ArrayList<Questions> allQuestions = new ArrayList<Questions>();
    Activity testActivity = getActivity();
    private Questions currentQuestion;
    private boolean answered;

    private int totalQuestions;
    private int counter = 0;

    private int totalCorrect;
    private int totalIncorrect;

    private int score;
    private Long startTime, endTime, totalTime;

    private TextView tvQuestion, tvScore, tvQuestionNumber, tvCorrect, tvIncorrect;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button NextButton;
    private ImageButton soundButton;

    //MEDIA VARIABLES
    MediaPlayer snoverSound, murlocSound, pikachuSound, psyduckSound, squirtleSound, kricketuneSound, castformSound, zigzagoonSound, electrodeSound, zubatSound;

    ColorStateList dfRbColor;

    private void perform(View v){
        //OBTENER LOS OBJETOS POR ID
        tvQuestion = v.findViewById(R.id.TQuestion);

        tvQuestionNumber = v.findViewById(R.id.TQuestionNumber);
        tvScore = v.findViewById(R.id.TPoints);
        tvCorrect = v.findViewById(R.id.TCorrect);
        tvIncorrect = v.findViewById(R.id.TIncorrect);

        radioGroup = v.findViewById(R.id.radioGroup);
        rb1 = v.findViewById(R.id.rbOpt1);
        rb2 = v.findViewById(R.id.rbOpt2);
        rb3 = v.findViewById(R.id.rbOpt3);
        rb4 = v.findViewById(R.id.rbOpt4);

        soundButton = v.findViewById(R.id.sound);
        NextButton = v.findViewById(R.id.SendButton2);

        dfRbColor = rb1.getTextColors();

        snoverSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.snoversound);
        murlocSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.murlocsound);
        pikachuSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.pikachusound);
        psyduckSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.psyducksound);
        squirtleSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.squirtlesound);
        kricketuneSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.kricketunesound);
        castformSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.castformsound);
        zigzagoonSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.zigzagoonsound);
        electrodeSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.electrodesound);
        zubatSound = MediaPlayer.create(((TestActivity)getActivity()), R.raw.zubatsound);


        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentQuestion.getId()){
                    case 2:
                        snoverSound.start();
                        break;
                    case 4:
                        murlocSound.start();
                        break;
                    case 6:
                        pikachuSound.start();
                        break;
                    case 8:
                        squirtleSound.start();
                        break;
                    case 10:
                        psyduckSound.start();
                        break;
                    case 12:
                        kricketuneSound.start();
                        break;
                    case 14:
                        castformSound.start();
                        break;
                    case 16:
                        zigzagoonSound.start();
                        break;
                    case 18:
                        electrodeSound.start();
                        break;
                    case 20:
                        zubatSound.start();
                        break;
                }
            }
        });

        //Comportamiento del botón para enviar la respuesta y cambiar de pregunta
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprueba si se ha contestado la pregunta
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer(v);
                    }else{
                        //Toast.makeText(testActivity, "Select one option", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();

                }
            }
        });

    }

    private void showNextQuestion() {

        soundButton.setVisibility(View.INVISIBLE);  //Cuando se pase de pregunta, el botón siempre será invisible.

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if(counter < totalQuestions){
            currentQuestion = questionsList.get(counter);
            //questionsPool.remove(numberNextQ);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOpt1());
            rb2.setText(currentQuestion.getOpt2());
            rb3.setText(currentQuestion.getOpt3());
            rb4.setText(currentQuestion.getOpt4());
            counter++;
            if (currentQuestion.getId() <= 20 && currentQuestion.getId()%2 == 0){
                soundButton.setVisibility(View.VISIBLE);    //Activamos las preguntas con sonido para las mediaQuestions
            }
            NextButton.setText("Send");
            tvQuestionNumber.setText("Question: "+counter+"/"+totalQuestions);
            answered = false;
        }
        else{
            endTime = System.currentTimeMillis();   //TIMER MANAGEMENT
            totalTime = endTime - startTime;

            Intent intent = new Intent(((TestActivity)getActivity()), FinishActivity.class);
            intent.putExtra("TotalPoints" , score);
            intent.putExtra("TotalCorrect", totalCorrect);
            intent.putExtra("TotalIncorrect", totalIncorrect);
            intent.putExtra("TotalQuestions", totalQuestions);
            intent.putExtra("Counter", counter);
            intent.putExtra("time", totalTime);
            startActivity(intent);

        }
    }

    //Comprueba si la respuesta escogida es la correcta
    private void checkAnswer(View v){
        answered = true;
        RadioButton rbSelected = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNumber = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNumber == currentQuestion.getAnswer()){
            score+=10;
            totalCorrect++;
            tvScore.setText("Score: "+ score);
            tvCorrect.setText(totalCorrect + " correct");
        }
        else{
            score-=5;
            totalIncorrect++;
            tvScore.setText("Score: "+ score);
            tvIncorrect.setText(totalIncorrect + " Incorrect");
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getAnswer()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }

        if(counter < totalQuestions){
            NextButton.setText("Next");
        }
        else{
            NextButton.setText("Next");
        }
    }

    public TestFragment() {
        super(R.layout.fragment_test);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        savedInstanceState = getArguments();

        allQuestions = ((TestActivity)getActivity()).getQuestionsPool();


        //BUNDLE

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(((TestActivity)getActivity()));
        GamePreferences.getPreferences(pref, ((TestActivity)getActivity()));

        totalQuestions = GamePreferences.nQ;

        //totalQuestions = savedInstanceState.getInt("totalQuestions");
        startTime = savedInstanceState.getLong("startTime");

        fillQuestionsByNumber();

        perform(view);
        showNextQuestion();

    }

    private void fillQuestionsByNumber() {
        Random r = new Random();
        int numberNextQ;

        while(questionsList.size() < totalQuestions){
            numberNextQ = r.nextInt(allQuestions.size());
            questionsList.add(allQuestions.get(numberNextQ));
            allQuestions.remove(numberNextQ);
        }
    }


}