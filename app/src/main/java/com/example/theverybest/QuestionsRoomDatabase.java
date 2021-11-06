package com.example.theverybest;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Questions.class}, version = 1)
public abstract class QuestionsRoomDatabase extends RoomDatabase {
    private static QuestionsRoomDatabase INSTANCE;

    public abstract QuestionDAO questionDAO();
    public static synchronized QuestionsRoomDatabase getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionsRoomDatabase.class, "question_database").fallbackToDestructiveMigration().addCallback(RoomDBCallback).build();
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDBAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {

        private QuestionDAO questionDAO;

        private PopulateDBAsyncTask(QuestionsRoomDatabase db){
            questionDAO = db.questionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            questionDAO.insert(new Questions("Which is the heaviest Pokémon?", "Celesteela", "Groudon", "Steeelix", "Waylord", 1));
            return null;
        }
    }
}
