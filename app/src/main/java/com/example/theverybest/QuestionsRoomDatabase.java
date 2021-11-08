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
    private static volatile QuestionsRoomDatabase INSTANCE;

    public abstract QuestionDAO questionDAO();


    public static synchronized QuestionsRoomDatabase getInstance(final Context context){

        if (INSTANCE == null){
            //Create DB heree
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

        private final QuestionDAO questionDAO;

        private PopulateDBAsyncTask(QuestionsRoomDatabase db){
            questionDAO = db.questionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            questionDAO.deleteAll();

            questionDAO.insert(new Questions("Which is the heaviest Pokémon?", "Celesteela", "Groudon", "Steeelix", "Waylord", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Illomon", "Snover", "Carnivine", "Victini", 2));
            questionDAO.insert(new Questions("Which Pokemon can evolve into 8 different forms, depending on how it is raised?", "Ditto", "Metapod", "Pikachu", "Eevee", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Arceus", "Murlocmon", "Not a Pokemon XD", "Spinda", 3));
            questionDAO.insert(new Questions("Which is the lightest Pokémon?", "Kartana", "Mimikyu", "Rotom", "Wishiwashi", 1));
            //5
            questionDAO.insert(new Questions("Who's that Pokemon?", "Pichu", "Pikachu", "Raichu", "Alolan Raichu", 2));
            questionDAO.insert(new Questions("Which of these is NOT an evolutionary stone?", "Fire Stone", "Water Stone", "Thunder Stone", "Dragon Stone", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Bulbasaur", "Squirtmon", "Squirtle", "Spinda", 3));
            questionDAO.insert(new Questions("What Pokémon can be resurrected from the Helix Fossil?", "Omanyte", "Aerodactyl", "Kabuto", "Cloyster", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Chansey", "Psyduck", "Jigglypluf", "Spinda", 2));
            //10
            questionDAO.insert(new Questions("Of the following Pokémon, which is the smallest?", "Ralts", "Whismur", "Roselia", "Joltik", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Kricketune", "Agumon", "Gyarados", "Spinda", 1));
            questionDAO.insert(new Questions("Which Pokémon isn’t an evolution of Eevee?", "Lumineon", "Sylveon", "Leafeon", "Glaceon", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Bergmite", "Castform", "Lickitung", "Cosmog", 2));
            questionDAO.insert(new Questions("Which of these Pokémon is told to be the most beautiful?", "Milotic", "Gardevoir", "Primarina", "Ninetales", 1));
            //15
            questionDAO.insert(new Questions("Who's that Pokemon?", "Gyarados","Exeggutor","Zigzagoon","Linoone",3));
            questionDAO.insert(new Questions("Which is the cheapest Poke Ball you can buy?","Ultra Ball","Super Ball","Poke ball","Honor Ball",3));
            questionDAO.insert(new Questions("Who's that Pokemon?", "Digglet","Geodude","Onix","Zubat", 4));
            questionDAO.insert(new Questions("Which item you should choose to wake Snorlax?", "Potion","Poke flute","Poke Ball","Revive",2));
            questionDAO.insert(new Questions("Who's that Pokemon?","Eelektross", "Voltrode", "Electrode", "Voltorb", 3));


            return null;
        }
    }
}
