package com.example.theverybest;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionsRepository {

    private QuestionDAO mQuestionDato;
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsRepository(Application app){
        //Este método está en QuestionsRoomDatabase
        QuestionsRoomDatabase db = QuestionsRoomDatabase.getInstance(app);
        mQuestionDato = db.questionDAO();
        //Esto coge todas las preguntas
        mAllQuestions = mQuestionDato.getAllQuestions();

    }

    //Esto devuelve todas las preguntas :D
    public LiveData<List<Questions>> getmAllQuestions(){
        return mAllQuestions;
    }
}
