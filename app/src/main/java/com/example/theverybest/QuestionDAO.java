package com.example.theverybest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Query("SELECT * from QUESTIONS_TABLE")
    LiveData<List<Questions>> getAllQuestions();

    @Insert void insert(Questions questions);
}
