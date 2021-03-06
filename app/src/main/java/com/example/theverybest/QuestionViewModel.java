package com.example.theverybest;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionsRepository mRepository;
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionViewModel(Application app){
        super(app);
        mRepository = new QuestionsRepository(app);
        mAllQuestions = mRepository.getmAllQuestions();

    }
    public LiveData<List<Questions>> getmAllQuestions(){
        return mAllQuestions;
    }
}
