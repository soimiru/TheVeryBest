package com.example.theverybest.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.theverybest.FinishActivity;
import com.example.theverybest.QuestionViewModel;
import com.example.theverybest.Questions;
import com.example.theverybest.R;
import com.example.theverybest.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment {

    private ArrayList<Questions> questionsList = new ArrayList<Questions>();
    private Questions currentQuestion;
    private boolean answered;

    private int totalQuestions;
    private int counter = 0;

    private int totalCorrect;
    private int totalIncorrect;

    private int score;

    private TextView tvQuestion, tvScore, tvQuestionNumber, tvCorrect, tvIncorrect;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button NextButton;

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

        NextButton = v.findViewById(R.id.SendButton2);

        dfRbColor = rb1.getTextColors();


        //Comportamiento del botón para enviar la respuesta y cambiar de pregunta
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprueba si se ha contestado la pregunta
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer(v);
                    }else{
                        //Toast.makeText(TestActivity.this, "Select one option", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();

                }
            }
        });

    }

    private void showNextQuestion() {

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
            NextButton.setText("Send");
            tvQuestionNumber.setText("Question: "+counter+"/"+totalQuestions);
            answered = false;
        }
        else{
            Intent intent = new Intent(((TestActivity)getActivity()), FinishActivity.class);
            intent.putExtra("TotalPoints" , score);
            intent.putExtra("TotalCorrect", totalCorrect);
            intent.putExtra("TotalIncorrect", totalIncorrect);
            intent.putExtra("TotalQuestions", totalQuestions);
            intent.putExtra("Counter", counter);
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


        //Base de datos
        QuestionViewModel questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        questionViewModel.getmAllQuestions().observe(getActivity(), new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {

                if(questionsList == null){
                    /*questionsList = new ArrayList<Questions>();

                    while(questions.size() > 0){
                        questionsList.add(questions.remove(0));
                    }*/


                    questionsList = ((TestActivity)getActivity()).getQuestionsPool();
                    perform(view);
                    showNextQuestion();

                }
            }
        });


        savedInstanceState = getArguments();

        questionsList = ((TestActivity)getActivity()).getQuestionsPool();

        totalQuestions = savedInstanceState.getInt("totalQuestions");



    }
}