package com.example.battleshipproject.pacificbattleship;


import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreFragment extends Fragment {



    DatabaseHelper myDb;
    private ArrayList<Competitor> allEasyCompetitor;
    private int rank;
    ListView mListView;

    public HighscoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_highscore, container, false);
        final ImageButton highscoreButton = (ImageButton)view.findViewById(R.id.highscore2);
        final ImageButton easyButton = (ImageButton)view.findViewById(R.id.easy_button);
        final ImageButton mediumButton = (ImageButton)view.findViewById(R.id.medium_button);
        final ImageButton hardButton = (ImageButton)view.findViewById(R.id.hard_button);
        myDb = new DatabaseHelper(getActivity());
        mListView = (ListView)view.findViewById(R.id.listView);

        allEasyCompetitor = new ArrayList<>();

        Cursor res = myDb.getAllData("Easy");
//        if(res.getCount() == 0) {
//            // show message
//            //showMessage("Error","Nothing found eeee");
//            return;
//        }

        StringBuffer buffer = new StringBuffer();
        rank=1;
        while (res.moveToNext()) {

            allEasyCompetitor.add(new Competitor(res.getString(1),res.getString(2),String.valueOf(rank)));
            rank++;
        }

        PersonListAdapter adapter = new PersonListAdapter(getActivity(), R.layout.adapter_view_layout, allEasyCompetitor);
        mListView.setAdapter(adapter);


        easyButton.setOnTouchListener(new View.OnTouchListener() {
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

                        myDb = new DatabaseHelper(getActivity());
                        mListView = (ListView)view.findViewById(R.id.listView);

                        allEasyCompetitor = new ArrayList<>();

                        Cursor res = myDb.getAllData("Easy");
//        if(res.getCount() == 0) {
//            // show message
//            //showMessage("Error","Nothing found eeee");
//            return;
//        }

                        StringBuffer buffer = new StringBuffer();
                        rank=1;
                        while (res.moveToNext()) {

                            allEasyCompetitor.add(new Competitor(res.getString(1),res.getString(2),String.valueOf(rank)));
                            rank++;
                        }

                        PersonListAdapter adapter = new PersonListAdapter(getActivity(), R.layout.adapter_view_layout, allEasyCompetitor);
                        mListView.setAdapter(adapter);

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



        mediumButton.setOnTouchListener(new View.OnTouchListener() {
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

                        myDb = new DatabaseHelper(getActivity());
                        mListView = (ListView)view.findViewById(R.id.listView);

                        allEasyCompetitor = new ArrayList<>();

                        Cursor res = myDb.getAllData("Medium");
//        if(res.getCount() == 0) {
//            // show message
//            //showMessage("Error","Nothing found eeee");
//            return;
//        }

                        StringBuffer buffer = new StringBuffer();
                        rank=1;
                        while (res.moveToNext()) {

                            allEasyCompetitor.add(new Competitor(res.getString(1),res.getString(2),String.valueOf(rank)));
                            rank++;
                        }

                        PersonListAdapter adapter = new PersonListAdapter(getActivity(), R.layout.adapter_view_layout, allEasyCompetitor);
                        mListView.setAdapter(adapter);

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

        hardButton.setOnTouchListener(new View.OnTouchListener() {
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

                        myDb = new DatabaseHelper(getActivity());
                        mListView = (ListView)view.findViewById(R.id.listView);

                        allEasyCompetitor = new ArrayList<>();

                        Cursor res = myDb.getAllData("Hard");
//        if(res.getCount() == 0) {
//            // show message
//            //showMessage("Error","Nothing found eeee");
//            return;
//        }

                        StringBuffer buffer = new StringBuffer();
                        rank=1;
                        while (res.moveToNext()) {

                            allEasyCompetitor.add(new Competitor(res.getString(1),res.getString(2),String.valueOf(rank)));
                            rank++;
                        }

                        PersonListAdapter adapter = new PersonListAdapter(getActivity(), R.layout.adapter_view_layout, allEasyCompetitor);
                        mListView.setAdapter(adapter);

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

                        getFragmentManager().beginTransaction().replace(R.id.fragment_placeHolder, new HomescreenFragment()).commit();

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

}
