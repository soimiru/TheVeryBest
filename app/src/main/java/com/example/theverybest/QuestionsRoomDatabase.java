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
            questionDAO.insert(new Questions("Where does the skull of Cubone come from?", "From its murdered victim", "From its dead mother", "From its dead father", "From the store", 2));
            questionDAO.insert(new Questions("Which Pokemon can evolve into 8 different forms, depending on how it is raised?", "Ditto", "Metapod", "Pikachu", "Eevee", 4));
            questionDAO.insert(new Questions("How many types of Pokémon are there?", "10 types", "15 types", "18 types", "21 types", 3));
            questionDAO.insert(new Questions("Which is the lightest Pokémon?", "Kartana", "Mimikyu", "Rotom", "Wishiwashi", 1));
            questionDAO.insert(new Questions("What's the most effective Poke Ball?", "SuperBall", "MasterBall", "UltraBall", "HonorBall", 2));
            questionDAO.insert(new Questions("Which of these is NOT an evolutionary stone?", "Fire Stone", "Water Stone", "Thunder Stone", "Dragon Stone", 4));
            questionDAO.insert(new Questions("What type of Pokémon is Mewtwo?", "Sinister", "Fairy", "Psychic", "Fighting", 3));
            questionDAO.insert(new Questions("What Pokémon can be resurrected from the Helix Fossil?", "Omanyte", "Aerodactyl", "Kabuto", "Cloyster", 1));
            questionDAO.insert(new Questions("Which of the following Pokémon seems to have a headache at all times?", "Chansey", "Psyduck", "Jigglypluf", "Spinda", 2));

            return null;
        }
    }
}
