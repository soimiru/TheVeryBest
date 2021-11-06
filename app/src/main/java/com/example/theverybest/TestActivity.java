package com.example.theverybest;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    //Declaración de variables para utilizar los recursos del layout
    private TextView tvQuestion, tvScore, tvQuestionNumber, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button NextButton;

    //Declaración de variables para el comportamiento
    int totalQuestions;
    int counter = 0;
    int score;
    int totalCorrect;
    int totalIncorrect;

    Random r = new Random();
    CountDownTimer countDownTimer;
    ColorStateList dfRbColor;
    boolean answered;

    private Question currentQuestion;
    private List<Question> questionsPool;

    //BASE DE DATOS
    private QuestionViewModel questionViewModel;
    private List<Questions> questionsListFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //MAIN CODE
        //Inicialización de las variables
        Boolean hardmode = getIntent().getBooleanExtra("Hardmode", false);
        totalQuestions = getIntent().getIntExtra("NumberQuestions", 5);
        totalQuestions-=2;
        //Toast.makeText(TestActivity.this, "Numero preguntas " + totalQuestions, Toast.LENGTH_SHORT).show();

        //Base de datos
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                Toast.makeText(TestActivity.this, "Get it!", Toast.LENGTH_LONG).show();
                fetchDataBase(questions);
            }
        });

        questionsPool = new ArrayList<>();
        tvQuestion = findViewById(R.id.TQuestion);
        tvScore = findViewById(R.id.TPoints);
        tvQuestionNumber = findViewById(R.id.TQuestionNumber);
        tvTimer = findViewById(R.id.TTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rbOpt1);
        rb2 = findViewById(R.id.rbOpt2);
        rb3 = findViewById(R.id.rbOpt3);
        rb4 = findViewById(R.id.rbOpt4);

        NextButton = findViewById(R.id.SendButton);

        dfRbColor = rb1.getTextColors();

        //Se rellena el ArrayList de preguntas
        fillPool(hardmode);
        //Se muestra la primera pregunta
        showNextQuestion();

        //Comportamiento del botón para enviar la respuesta y cambiar de pregunta
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprueba si se ha contestado la pregunta
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();
                    }else{
                        Toast.makeText(TestActivity.this, "Select one option", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();

                }
            }
        });

    }

    //Comprueba si la respuesta escogida es la correcta
    private void checkAnswer(){
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNumber = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNumber == currentQuestion.getRightAnswer()){
            score+=10;
            totalCorrect++;
            tvScore.setText("Score: "+ score);
        }
        else{
            score-=5;
            totalIncorrect++;
            tvScore.setText("Score: "+ score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getRightAnswer()){
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

    //Muestra la siguiente pregunta o, en caso de ser la última, termina el test
    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if(counter < totalQuestions){
            timer();
            int numberNextQ = r.nextInt(9-counter);
            currentQuestion = questionsPool.get(numberNextQ);
            questionsPool.remove(numberNextQ);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOpt1());
            rb2.setText(currentQuestion.getOpt2());
            rb3.setText(currentQuestion.getOpt3());
            rb4.setText(currentQuestion.getOpt4());
            counter++;
            NextButton.setText("Send");
            tvQuestionNumber.setText("Question: "+counter+"/"+(totalQuestions+2));
            answered = false;

        }
        else{
            Intent intent = new Intent(TestActivity.this, ImagesActivity.class);
            intent.putExtra("TotalPoints" , score);
            intent.putExtra("TotalCorrect", totalCorrect);
            intent.putExtra("TotalIncorrect", totalIncorrect);
            intent.putExtra("TotalQuestions", totalQuestions);
            intent.putExtra("Counter", counter);
            startActivity(intent);

        }
    }

    //Método para implementar la cuenta atrás
    private void timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time left: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }


    private void fetchDataBase(List<Questions> questions){
        questionsListFromDB = questions;

    }

    //Lista de preguntas
    private void fillPool(Boolean hardmode) {
        if(hardmode){
                questionsPool.add(new Question("Which is the heaviest Pokémon?", "Celesteela", "Groudon", "Steeelix", "Waylord", 1));
                questionsPool.add(new Question("Where does the skull of Cubone come from?", "From its murdered victim", "From its dead mother", "From its dead father", "From the store", 2));
                questionsPool.add(new Question("Which Pokemon can evolve into 8 different forms, depending on how it is raised?", "Ditto", "Metapod", "Pikachu", "Eevee", 4));
                questionsPool.add(new Question("How many types of Pokémon are there?", "10 types", "15 types", "18 types", "21 types", 3));
                questionsPool.add(new Question("Which is the lightest Pokémon?", "Kartana", "Mimikyu", "Rotom", "Wishiwashi", 1));
                questionsPool.add(new Question("What's the most effective Poke Ball?", "SuperBall", "MasterBall", "UltraBall", "HonorBall", 2));
                questionsPool.add(new Question("Which of these is NOT an evolutionary stone?", "Fire Stone", "Water Stone", "Thunder Stone", "Dragon Stone", 4));
                questionsPool.add(new Question("What type of Pokémon is Mewtwo?", "Sinister", "Fairy", "Psychic", "Fighting", 3));
                questionsPool.add(new Question("What Pokémon can be resurrected from the Helix Fossil?", "Omanyte", "Aerodactyl", "Kabuto", "Cloyster", 1));
                questionsPool.add(new Question("Which of the following Pokémon seems to have a headache at all times?", "Chansey", "Psyduck", "Jigglypluf", "Spinda", 2));

        }
        else{
                questionsPool.add(new Question("Which color is Pikachu?", "Red", "Blue", "Yellow", "Green", 3));
                questionsPool.add(new Question("Which is the first Pokémon in the Pokedex with the number 001?", "Caterpie", "Charmander", "Bulbasaur", "Squirtle", 3));
                questionsPool.add(new Question("If you need to buy supplies, where do you go?", "Pokémon Center", "Poke Mart", "Gym", "Poke Lab", 2));
                questionsPool.add(new Question("If you need to revive your fainted Pokémon to full health, where do you go?", "Pokemon Center", "Poke Mart", "Gym", "Poke Lab", 1));
                questionsPool.add(new Question("What is NOT a type of a starter Pokémon?", "Grass", "Bug", "Water", "Fire", 2));
                questionsPool.add(new Question("What item heals a Pokemon's HP by a small amount?", "Potion", "Awakening", "Antidote", "Full Heal", 1));
                questionsPool.add(new Question("Which Pokémon is able to mimic others?", "Ditto", "Muk", "Porygon", "Mr. Mime", 1));
                questionsPool.add(new Question("Which Pokémon has an Alolan form?", "Exeggutor", "Chansey", "Kangaskhan", "Seviper", 1));
                questionsPool.add(new Question("Which of these is NOT the name of a real Pokémon?", "Reshiram", "Agumon", "Dusclops", "Raikou", 2));
                questionsPool.add(new Question("What is the most common type of Pokémon?", "Water", "Fire", "Grass", "Normal", 1));
        }
    }

}