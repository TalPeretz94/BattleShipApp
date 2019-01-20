package com.example.battleshipproject.pacificbattleship;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class gameOverActivity extends AppCompatActivity {

    ImageButton playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ImageView iv = findViewById(R.id.victoryordefeat);
        playAgain = findViewById(R.id.playagain);
        Animation animation = AnimationUtils.loadAnimation(gameOverActivity.this,R.anim.lefttoright);
        playAgain.startAnimation(animation);


        playAgain.setOnTouchListener(new View.OnTouchListener() {
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


                        onBackPressed();

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




        Intent intent = getIntent();

        Bundle b = intent.getBundleExtra(gameActivity.BUNDLE_KEY);

        if (b != null) {

            String passedString = b.getString(gameActivity.STRING_KEY);

            if (passedString != null) {
                if (passedString.equalsIgnoreCase("victory")) {
                    iv.setImageResource(R.drawable.victoryscreen);
                } else {
                    iv.setImageResource(R.drawable.defeatscreen);
                }
            }
        }
    }
}
