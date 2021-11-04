package com.example.theverybest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theverybest.fragments.PreferencesFragment;

public class Configuration extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerPreferences, new PreferencesFragment()).commit();


    }
}