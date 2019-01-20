package com.example.battleshipproject.pacificbattleship;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.View;



/*
Yanai Lipshitz  302205232
Tal Peretz      312468929
*/

public class mainActivity extends AppCompatActivity {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set desired fragment for the first time
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // The id specified here identifies which ViewGroup to
        // append the Fragment to.
        ft.add(R.id.fragment_placeHolder, new HomescreenFragment());
        ft.commit();
    }

    /*public void switch_fragment(View view) {
        Fragment newFragment = null;
        switch (view.getId()) {
            case R.id.highscore2:
                newFragment = new HomescreenFragment();
                break;
            case R.id.highscore1:
                newFragment = new HighscoreFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_placeHolder, newFragment);
        ft.commit();
    }*/


}
