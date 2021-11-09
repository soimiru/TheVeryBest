package com.example.theverybest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.theverybest.fragments.InitFragment;
import com.example.theverybest.fragments.PlayerManagementFragment;
import com.example.theverybest.fragments.RankingFragment;
import com.example.theverybest.fragments.RegistryPlayerFragment;
import com.example.theverybest.interfaces.IComunicationFragments;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IComunicationFragments,
        InitFragment.OnFragmentInteractionListener ,
        AdapterView.OnItemSelectedListener,
        PlayerManagementFragment.OnFragmentInteractionListener {

    private int NumberQuestionsSelected;

    Fragment initFragment;
    Fragment registryPlayer;
    Fragment playerManagement;
    Fragment rankingFragment;

    public QuestionViewModel questionViewModel ;
    private ArrayList<Questions> questionsPool;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MAIN CODE

        //FRAGMENTO INICIAL
        initFragment = new InitFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, initFragment).commit();

        Utilities.getAvatarList();
        Utilities.getPlayersList(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, Utilities.PLAYERS_BD, null, 1);

        registryPlayer = new RegistryPlayerFragment();
        playerManagement = new PlayerManagementFragment();
        rankingFragment = new RankingFragment();

        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                if(questionsPool == null){
                    questionsPool = new ArrayList<Questions>();

                    while(questions.size() > 0){
                        questionsPool.add(questions.remove(0));
                    }
                }
            }
        });


    }

    public void startTest(){

        PreferenceManager.setDefaultValues(MainActivity.this, R.xml.preferences, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        GamePreferences.getPreferences(preferences, MainActivity.this);

        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("Hardmode", GamePreferences.gameMode);
        intent.putExtra("NumberQuestions", GamePreferences.questionsN);
        intent.putExtra("QuestionsPool", questionsPool);
        startActivity(intent);
    }

    public void goToConfiguration(){
        Intent intent = new Intent(this, Configuration.class);
        startActivity(intent);
    }

    public void goToRankings(){
        getSupportFragmentManager().beginTransaction().replace( R.id.containerFragments, rankingFragment).commit();
    }

    public AlertDialog dialogPlayer(){
        //GENERA EL DIALOGO
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Player Manager")
                .setMessage("Do you wish to sign up as a new player"+
                        " or log in as an existent one\n\n"+ 
                        "You can select and modify your profile if you select MODIFY")
                .setNegativeButton("SIGN UP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), "SIGN UP", Toast.LENGTH_SHORT).show();
                        //Esto puede no funcionar
                        getSupportFragmentManager().beginTransaction().replace( R.id.containerFragments,registryPlayer).commit();

                    }
                }).setPositiveButton("MODIFY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getSupportFragmentManager().beginTransaction().replace( R.id.containerFragments,playerManagement).commit();
                    }
                });
        return builder.create();
    }

    public void managePlayer(){
        dialogPlayer().show();

    }

    @Override
    public void showMenu() {
        Utilities.getAvatarList();
        Utilities.getPlayersList(this);
        //Se refresca tod

        //Cargar fragment de inicio
        getSupportFragmentManager().beginTransaction().replace( R.id.containerFragments,initFragment).commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberQuestionsSelected = Integer.parseInt(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFragmentInteraction(Uri uri){


    }

}