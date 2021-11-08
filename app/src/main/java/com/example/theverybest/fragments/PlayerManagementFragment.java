package com.example.theverybest.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.ConexionSQLiteHelper;
import com.example.theverybest.GamePreferences;
import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.adapters.AvatarAdapter;
import com.example.theverybest.adapters.PlayerAdapter;
import com.example.theverybest.interfaces.IComunicationFragments;
import com.example.theverybest.vo.PlayerVO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerManagementFragment extends Fragment {

    View view;
    Activity activity = getActivity();
    IComunicationFragments interfaceComunicationFragments;
    PlayerVO playerSelected;
    int deleteEvent = 0;


    RecyclerView recyclerAvatars, recyclerPlayers;
    EditText nickField;
    TextView barSelected;

    ImageButton backButton;

    FloatingActionButton fabConfirm, fabDelete, fabUpdate;

    public PlayerManagementFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PlayerManagementFragment newInstance(String param1, String param2) {
        PlayerManagementFragment fragment = new PlayerManagementFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicationFragments =(IComunicationFragments) activity;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player_management, container, false);

        recyclerAvatars = view.findViewById(R.id.recyclerAvatarsId);
        recyclerPlayers = view.findViewById(R.id.recyclerPlayers);
        nickField = view.findViewById(R.id.nicknameInputUpdate);
        backButton = view.findViewById(R.id.backButton);
        barSelected = view.findViewById(R.id.selectionBar);

        fabConfirm = view.findViewById(R.id.idfabConfirm);
        fabUpdate = view.findViewById(R.id.idfabUpdate);
        fabDelete = view.findViewById(R.id.idfabDelete);

        recyclerPlayers.setLayoutManager(new LinearLayoutManager(this.activity));
        recyclerPlayers.setHasFixedSize(true);

        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.activity, 2));
        recyclerAvatars.setHasFixedSize(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteEvent == 0){
                    interfaceComunicationFragments.showMenu();
                }
                else{
                    Toast.makeText(activity, "Select a player.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayer();
                fillPlayersAdapter();
            }
        });
        
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Comprobamos que hay un jugador seleccionado para poder borrarlo
                if (nickField.getText().toString() != null && !nickField.getText().toString().trim().equals("")){
                    deleteDialog().show();
                }else{
                    Toast.makeText(activity, "Select one player.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Almacena en las preferencias del sistema el usuario seleccionado
        fabConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nickField.getText().toString() != null && !nickField.getText().toString().trim().equals("")){
                    //AL SELECCIONAR UN NUEVO JUGADOR, GUARDAMOS SUS DATOS EN LAS PREFERENCIAS.
                    GamePreferences.nicknamePreferences = "Welcome "+nickField.getText().toString();
                    GamePreferences.avatarIDPreferences = Utilities.selectedAvatar.getId();
                    GamePreferences.playerIDPreferences = playerSelected.getId();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                    GamePreferences.setPlayerPreferences(preferences, activity);

                    deleteEvent = 0;
                    interfaceComunicationFragments.showMenu();
                    Toast.makeText(activity, "Welcome " + GamePreferences.nicknamePreferences + " "+ GamePreferences.avatarIDPreferences, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(activity, "Select one player.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fillAvatarsAdapter(0);
        fillPlayersAdapter();

        return view;
    }

    public AlertDialog deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("WARNING!")
                .setMessage("Do you wish to delete "+
                        playerSelected.getName().toUpperCase(Locale.ROOT)+
                        " ?")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletePlayer();
                        fillPlayersAdapter();
                        deleteEvent = 1;
                    }
                }).setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();

    }

    private void deletePlayer() {

        if (nickField.getText().toString() != null && !nickField.getText().toString().trim().equals("")){
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity, Utilities.PLAYERS_BD, null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            int idResult = db.delete(Utilities.PLAYERS_BD,Utilities.PLAYERS_ID+"="+playerSelected.getId(),null);

            if(idResult != -1){
                Toast.makeText(activity, "Player was deleted.", Toast.LENGTH_SHORT).show();
                nickField.setText("");
                recyclerPlayers.scrollToPosition(playerSelected.getId());
                recyclerAvatars.scrollToPosition(0);
                Utilities.getPlayersList(activity);
            }else{
                Toast.makeText(activity, "Player was not deleted.", Toast.LENGTH_SHORT).show();

            }
            db.close();
        }else{
            Toast.makeText(activity, "Select one player.", Toast.LENGTH_SHORT).show();
        }


    }

    private void updatePlayer() {
        if (nickField.getText().toString() != null && !nickField.getText().toString().trim().equals("")){
            String nick = nickField.getText().toString();
            int avID = Utilities.selectedAvatar.getId();
            int bestSc = 0;

            //Conexión BD
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity, Utilities.PLAYERS_BD, null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            //Values funciona con clave-valor
            ContentValues values = new ContentValues();
            values.put(Utilities.PLAYERS_AVATAR, avID);
            values.put(Utilities.PLAYERS_NAME, nick);
            values.put(Utilities.PLAYERS_BESTSCORE, bestSc);

            //QUERY PARA EL UPDATE
            int idResult = db.update(Utilities.PLAYERS_BD, values, Utilities.PLAYERS_ID+"="+playerSelected.getId(), null);

            if (idResult != -1){
                Toast.makeText(activity, nick +" was updated.", Toast.LENGTH_SHORT).show();
                recyclerPlayers.scrollToPosition(playerSelected.getId()-1);
                Utilities.getPlayersList(activity);
            }
            else{
                Toast.makeText(activity, "Couldn't update player.", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
        else{
            Toast.makeText(activity, "Incorrect nickname", Toast.LENGTH_SHORT).show();

        }
    }

    private void fillPlayersAdapter() {
        PlayerAdapter playerAdapter = new PlayerAdapter(Utilities.playersList);

        playerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelected = Utilities.playersList.get(recyclerPlayers.getChildAdapterPosition(view));
                nickField.setText(playerSelected.getName());

                Utilities.selectedAvatar = Utilities.avatarList.get(playerSelected.getAvatar()-1);
                fillAvatarsAdapter(playerSelected.getAvatar());
            }
        });

        recyclerPlayers.setAdapter(playerAdapter);
    }

    private void fillAvatarsAdapter(int avatarID) {
        Utilities.selectedAvatarID = avatarID;
        AvatarAdapter avatarAdapter = new AvatarAdapter(Utilities.avatarList);
        recyclerAvatars.scrollToPosition(avatarID-1);
        recyclerAvatars.setAdapter(avatarAdapter);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
}