package com.example.theverybest.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.theverybest.R;
import com.example.theverybest.interfaces.IComunicationFragments;

public class InitFragment extends Fragment {


    View view;
    Activity mainActivity = getActivity();
    IComunicationFragments interfaceComunicationFragments;
    ImageButton playBTN, settingsBTN, playerBTN, rankingBTN;

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

        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            mainActivity = (Activity) context;
            interfaceComunicationFragments =(IComunicationFragments) mainActivity;

        }
    }
}