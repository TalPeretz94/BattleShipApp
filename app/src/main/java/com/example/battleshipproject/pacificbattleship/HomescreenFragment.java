package com.example.battleshipproject.pacificbattleship;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomescreenFragment extends Fragment {

    int i = 0;
    //static boolean musicon = true;
    TextView diff;
    ImageView diffImg;
    //private MediaPlayer mp;
    int[] difLvl = {R.string.easy, R.string.medium, R.string.hard};
    int[] photos = {R.drawable.easy1, R.drawable.medium1, R.drawable.hard1};
    final static String STRING_KEY = "STRING_KEY";
    final static String BOOLEAN_KEY = "BOOLEAN_KEY";
    final static String BUNDLE_KEY = "BUNDLE_KEY";


    public HomescreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_homescreen, container, false);
        final ImageButton moreDifButton = (ImageButton)view.findViewById(R.id.moreDif);
        final ImageButton lessDifButton = (ImageButton)view.findViewById(R.id.lessDif);
        final ImageButton start = (ImageButton)view.findViewById(R.id.start);
        final ImageButton highscoreButton = (ImageButton)view.findViewById(R.id.highscore1);

        diffImg = (ImageView)view.findViewById(R.id.difficultylvl);
        diff = (TextView)view.findViewById(R.id.difficulty);
        getData();





        moreDifButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        moreDifClick(v);

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        lessDifButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        lessDifClick(v);

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        //stopPlaying();
                        Intent intent = new Intent(getActivity(), gameActivity.class);
                        Bundle b = new Bundle();

                        b.putString(STRING_KEY, (String) diff.getText());
                        //b.putBoolean(BOOLEAN_KEY,musicon);
                        saveData();
                        intent.putExtra(BUNDLE_KEY, b);
                        startActivity(intent);

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        highscoreButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        saveData();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_placeHolder, new HighscoreFragment()).commit();

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        return view;
    }

    public void moreDifClick(View view) {
        if (i < 2)
            i++;
        diff.setText(difLvl[i]);
        diffImg.setImageResource(photos[i]);
    }

    public void lessDifClick(View view) {
        if (i > 0)
            i--;
        diff.setText(difLvl[i]);
        diffImg.setImageResource(photos[i]);
    }

    public void saveData(){
        SharedPreferences loginData = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putInt("level",i);
        editor.apply();

    }

    public void getData(){
        SharedPreferences loginData = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        i = loginData.getInt("level",0);

        diff.setText(difLvl[i]);
        diffImg.setImageResource(photos[i]);
    }


}
