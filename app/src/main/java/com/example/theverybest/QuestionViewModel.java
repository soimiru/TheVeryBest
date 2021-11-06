package com.example.theverybest;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuestionViewModel extends ViewModel {

    private QuestionsRepository mRepository;
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionViewModel(Application app){
        //super(app);
        mRepository = new QuestionsRepository(app);
        mAllQuestions = mRepository.getmAllQuestions();

    }
    LiveData<List<Questions>> getmAllQuestions(){
        return mAllQuestions;
    }
}
