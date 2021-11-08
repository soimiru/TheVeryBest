package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.ConexionSQLiteHelper;
import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.adapters.AvatarAdapter;
import com.example.theverybest.interfaces.IComunicationFragments;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistryPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistryPlayerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    RecyclerView recyclerAvatar;
    Activity activity;
    IComunicationFragments interfaceComunicationFragments;

    FloatingActionButton fabRegister;
    EditText nickField;

    public RegistryPlayerFragment() {
        // Required empty public constructor
    }


    public static RegistryPlayerFragment newInstance(String param1, String param2) {
        RegistryPlayerFragment fragment = new RegistryPlayerFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //INFLAMOS LA VISTA
        view = inflater.inflate(R.layout.fragment_registry_player, container, false);
        recyclerAvatar = view.findViewById(R.id.recyclerAvatar);
        recyclerAvatar.setLayoutManager(new GridLayoutManager(this.activity, 2));
        recyclerAvatar.setHasFixedSize(true);

        nickField = view.findViewById(R.id.nicknameInput);  //NICKNAME
        fabRegister = view.findViewById(R.id.registerButton);   //BOTON COMMIT

        fabRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPlayer();
            }
        });

        //Adaptador
        final AvatarAdapter myAdapter = new AvatarAdapter(Utilities.avatarList);    //Carga la lista de los avatares
        recyclerAvatar.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicationFragments =(IComunicationFragments) activity;

        }
    }

    private void registerPlayer(){
        System.out.println("Esto funsiona");

        if (nickField.getText().toString() != null && !nickField.getText().toString().trim().equals("")){
            String nick = nickField.getText().toString()+"\n";
            int avID = Utilities.selectedAvatar.getId();
            int bestSc = 0;
            System.out.println(nick + " " + avID);

            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity, Utilities.PLAYERS_BD, null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilities.PLAYERS_AVATAR, avID);
            values.put(Utilities.PLAYERS_NAME, nick);
            values.put(Utilities.PLAYERS_BESTSCORE, bestSc);

            System.out.println(values.size());
            Long idResult = db.insert(Utilities.PLAYERS_BD, Utilities.PLAYERS_ID, values);

            if (idResult != -1){
                Toast.makeText(activity, "New player was registered.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(activity, "Couldn't register new player.", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
        else{
            Toast.makeText(activity, "Incorrect nickname", Toast.LENGTH_SHORT).show();

        }

    }
}