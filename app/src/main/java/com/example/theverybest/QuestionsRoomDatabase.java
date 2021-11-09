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
            questionDAO.insert(new Questions("Which is the heaviest Pokémon?", 0,"Celesteela", "Groudon", "Steeelix", "Waylord", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", 1,"Illomon", "Snover", "Carnivine", "Victini", 2));
            questionDAO.insert(new Questions("Which Pokemon can evolve into 8 different forms, depending on how it is raised?", 0,"Ditto", "Metapod", "Pikachu", "Eevee", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", 2,"Arceus", "Murlocmon", "Not a Pokemon XD", "Spinda", 3));
            questionDAO.insert(new Questions("Which is the lightest Pokémon?", 0,"Kartana", "Mimikyu", "Rotom", "Wishiwashi", 1));
            //5
            questionDAO.insert(new Questions("Who's that Pokemon?", 3,"Pichu", "Pikachu", "Raichu", "Alolan Raichu", 2));
            questionDAO.insert(new Questions("Which of these is NOT an evolutionary stone?", 0,"Fire Stone", "Water Stone", "Thunder Stone", "Dragon Stone", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", 4,"Bulbasaur", "Squirtmon", "Squirtle", "Spinda", 3));
            questionDAO.insert(new Questions("What Pokémon can be resurrected from the Helix Fossil?", 0,"Omanyte", "Aerodactyl", "Kabuto", "Cloyster", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", 5,"Chansey", "Psyduck", "Jigglypluf", "Spinda", 2));
            //10
            questionDAO.insert(new Questions("Of the following Pokémon, which is the smallest?", 0,"Ralts", "Whismur", "Roselia", "Joltik", 4));
            questionDAO.insert(new Questions("Who's that Pokemon?", 6,"Kricketune", "Agumon", "Gyarados", "Spinda", 1));
            questionDAO.insert(new Questions("Which Pokémon isn’t an evolution of Eevee?", 0,"Lumineon", "Sylveon", "Leafeon", "Glaceon", 1));
            questionDAO.insert(new Questions("Who's that Pokemon?", 7,"Bergmite", "Castform", "Lickitung", "Cosmog", 2));
            questionDAO.insert(new Questions("Which of these Pokémon is told to be the most beautiful?", 0,"Milotic", "Gardevoir", "Primarina", "Ninetales", 1));
            //15
            questionDAO.insert(new Questions("Who's that Pokemon?", 8,"Gyarados","Exeggutor","Zigzagoon","Linoone",3));
            questionDAO.insert(new Questions("Which is the cheapest Poke Ball you can buy?",0,"Ultra Ball","Super Ball","Poke ball","Honor Ball",3));
            questionDAO.insert(new Questions("Who's that Pokemon?", 9,"Digglet","Geodude","Onix","Zubat", 4));
            questionDAO.insert(new Questions("Which item you should choose to wake Snorlax?", 0,"Potion","Poke flute","Poke Ball","Revive",2));
            questionDAO.insert(new Questions("Who's that Pokemon?",10,"Eelektross", "Voltrode", "Electrode", "Voltorb", 3));
            //20
            questionDAO.insert(new Questions("Where does the skull of Cubone come from?",0, "From its murdered victim", "From its dead mother", "From its dead father", "From the store", 2));
            questionDAO.insert(new Questions("How many types of Pokémon are there?", 0,"10 types", "15 types", "18 types", "21 types", 3));
            questionDAO.insert(new Questions("What's the most effective Poke Ball?",0, "SuperBall", "MasterBall", "UltraBall", "HonorBall", 2));
            questionDAO.insert(new Questions("What type of Pokémon is Mewtwo?",0, "Sinister", "Fairy", "Psychic", "Fighting", 3));
            questionDAO.insert(new Questions("Which of the following Pokémon seems to have a headache at all times?", 0,"Chansey", "Psyduck", "Jigglypluf", "Spinda", 2));
            //25
            questionDAO.insert(new Questions("How many Unown are there?",0, "28", "46", "26", "30", 1));
            questionDAO.insert(new Questions("Which of these Pokémon does not evolve?",0, "Rayquaza", "Wingwull", "Lickilicky", "Grovile", 1));
            questionDAO.insert(new Questions("What Pokemon is correctly spelled?", 0,"Gayardos","Eggsecutor","Peliper","Lioone",3));
            questionDAO.insert(new Questions("Which is the most common Pokémon you can find in a cave?",0, "Digglet","Geodude","Onix","Zubat", 4));
            questionDAO.insert(new Questions("Who's the Pokémon that looks like a Poke Ball?",0,"Eelektross", "Voltrode", "Electrode", "Voltorb", 3));

            return null;
        }
    }
}
