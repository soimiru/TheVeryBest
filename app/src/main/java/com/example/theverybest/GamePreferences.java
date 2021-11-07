package com.example.theverybest;

import android.content.Context;
import android.content.SharedPreferences;

public class GamePreferences {

    public static final String GameMode="Hardmode";
    public static final String QuestionsNumber="NumberQuestions";

    public static String gameMode;
    public static boolean gM = false;
    public static String questionsN;
    public static int nQ;

    public static void getPreferences(SharedPreferences preferences, Context context){

        //GETTING PREFERENCES FOR GAMEMODE
        gameMode= preferences.getString(GameMode, "Keep it simple");

        if(gameMode.equals("Keep it simple")){
            gM = false;
        }
        else if(gameMode.equals("Make it hard")){
            gM = true;
        }

        //GETTING PREFERENCES FOR QUESTIONS NUMBER
        questionsN = preferences.getString(QuestionsNumber, "5 questions");

        if(questionsN.equals("5 questions")){
            nQ = 5;
        }
        else if(questionsN.equals("10 questions")){
            nQ = 10;
        }
        else if(questionsN.equals("15 questions")){
            nQ = 15;
        }

        String msj = "Modo juego: "+gameMode+"\n";
        msj+= "Q Number: "+ questionsN+"\n";

        //Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
    }
}
