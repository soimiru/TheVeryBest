package com.example.theverybest;

import android.content.Context;
import android.content.SharedPreferences;

public class GamePreferences {

    public static final String GameMode="Hardmode";
    public static final String QuestionsNumber="NumberQuestions";

    public static final String PLAYERID= "playerID";
    public static final String NICKNAME= "nickname";
    public static final String AVATARID= "avatarID";


    public static String gameMode;
    public static boolean gM = false;
    public static String questionsN;
    public static int nQ;

    public static int playerIDPreferences;
    public static String nicknamePreferences;
    public static int avatarIDPreferences;

    public static void setPlayerPreferences(SharedPreferences preferences, Context context){
        SharedPreferences.Editor editor = preferences.edit();
        System.out.println(nicknamePreferences + " " +avatarIDPreferences);
        editor.putString(PLAYERID, ""+ playerIDPreferences);
        editor.putString(NICKNAME, ""+ nicknamePreferences);
        editor.putString(AVATARID, ""+ avatarIDPreferences);
        editor.commit();

        System.out.println(nicknamePreferences + " " +avatarIDPreferences);
        getPreferences(preferences, context);
    }

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

        playerIDPreferences = Integer.parseInt(preferences.getString(PLAYERID, "0"));
        nicknamePreferences = preferences.getString(NICKNAME, "new trainer");
        avatarIDPreferences = Integer.parseInt(preferences.getString(AVATARID, "-1"));
    }
}
