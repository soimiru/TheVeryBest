package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.theverybest.GamePreferences;
import com.example.theverybest.R;
import com.example.theverybest.Utilities;
import com.example.theverybest.interfaces.IComunicationFragments;

public class InitFragment extends Fragment {


    View view;
    Activity mainActivity = getActivity();
    IComunicationFragments interfaceComunicationFragments;
    ImageButton playBTN, settingsBTN, playerBTN, rankingBTN;

    ImageView avatarImage;
    TextView nickText;

    public InitFragment() {
        // Required empty public constructor
    }

    public static InitFragment newInstance(String param1, String param2) {
        InitFragment fragment = new InitFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_init, container, false);

        playBTN = view.findViewById(R.id.playBTN);

        System.out.println("MAIN: "+GamePreferences.nicknamePreferences + " " +GamePreferences.avatarIDPreferences);

        //COMPORTAMIENTO START BUTTON
        playBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicationFragments.startTest();
            }
        });

        settingsBTN = view.findViewById(R.id.settingsBTN);

        //COMPORTAMIENTO SETTINGS BUTTON
        settingsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceComunicationFragments.goToConfiguration();
            }
        });

        playerBTN = view.findViewById(R.id.playerBTN);

        //COMPORTAMIENTO PLAYER BUTTON
        playerBTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                interfaceComunicationFragments.managePlayer();
            }

        });

        //PLAYER
        avatarImage = view.findViewById(R.id.MainAvatarImage);
        nickText = view.findViewById(R.id.MainNickText);
        nickText.setText(GamePreferences.nicknamePreferences);
        if(GamePreferences.avatarIDPreferences == 0){
            avatarImage.setImageResource(R.drawable.spindatest);
        }else{
            avatarImage.setImageResource(Utilities.avatarList.get(GamePreferences.avatarIDPreferences -1).getAvatarId());
        }

        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){

            PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            GamePreferences.getPreferences(preferences, context);

            mainActivity = (Activity) context;
            interfaceComunicationFragments =(IComunicationFragments) mainActivity;

        }
    }
}