package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.adapters.AvatarAdapter;
import com.example.theverybest.adapters.PlayerAdapter;
import com.example.theverybest.interfaces.IComunicationFragments;
import com.example.theverybest.vo.PlayerVO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayer();
            }
        });
        
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlayer();
            }
        });

        fabConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "CONFIRMAR", Toast.LENGTH_SHORT).show();
            }
        });

        fillAvatarsAdapter(0);
        fillPlayersAdapter();

        return view;
    }



    private void deletePlayer() {
        Toast.makeText(activity, "BORRADO", Toast.LENGTH_SHORT).show();
    }

    private void updatePlayer() {
        Toast.makeText(activity, "ACTUALIZADO", Toast.LENGTH_SHORT).show();
    }

    private void fillPlayersAdapter() {
        PlayerAdapter playerAdapter = new PlayerAdapter(Utilities.playersList);

        playerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSelected = Utilities.playersList.get(recyclerPlayers.getChildAdapterPosition(view));
                nickField.setText(playerSelected.getName());

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