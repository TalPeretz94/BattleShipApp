package com.example.battleshipproject.pacificbattleship;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class gameActivity extends AppCompatActivity implements ServiceConnection,SensorService.StormInterface {


    private Game mGame;
    private GridView gridPlayer;
    private GridView gridComputer;
    private boolean victory;
    private boolean defeat;
    private final String VICTORY = "victory";
    private final String DEFEAT = "defeat";
    final static String STRING_KEY = "GAMEOVER_KEY";
    private static int count;
    final static String BUNDLE_KEY = "BUNDLE_KEY";
    private static String passedString = "Easy";
    private ImageView iv;
    private AnimationDrawable stormAnim;
    private DatabaseHelper myDb;
    private static final String TAG = "gameActivity";
    boolean firstTime;
    boolean isBound = false;
    private SensorService sensorService;
    private ImageView iv1;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        count=0;
        ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getApplicationContext(), SensorService.class);
        this.getApplicationContext().startService(intent);

        Intent in = getIntent();
        Bundle b = in.getBundleExtra(HomescreenFragment.BUNDLE_KEY);



        if (b != null) {
            passedString = b.getString(HomescreenFragment.STRING_KEY);
        }

        mGame = new Game(passedString);
        gridPlayer = (GridView) findViewById(R.id.gridview1);
        gridPlayer.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        gridComputer = (GridView) findViewById(R.id.gridview2);
        gridComputer.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        gridPlayer.setNumColumns(mGame.getPlayerBoard().getbSize());
        gridComputer.setNumColumns(mGame.getComputerBoard().getbSize());
        gridPlayer.setAdapter(new TileAdapter(getApplicationContext(), mGame.getPlayerBoard()));
        gridComputer.setAdapter(new TileAdapter(getApplicationContext(), mGame.getComputerBoard()));
        gridComputer.setOnItemClickListener(onItemClickListener);
        firstTime = true;
        iv1 = findViewById(R.id.stormiv);
        iv1.setBackgroundResource(R.drawable.storm_animation);
        iv1.setVisibility(View.INVISIBLE);
        stormAnim = (AnimationDrawable) iv1.getBackground();
        myDb = new DatabaseHelper(this);


    }



    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            mGame.getRealPlayer().setPosition(position);
            if (!defeat) {

                victory = mGame.ComputerAndPlayerShoot();
                gridComputer.setEnabled(false);

                if (victory) {
                    gameOverScreen(VICTORY);
                }

                view.setOnClickListener(null);
                //gridComputer.getAdapter().getView(position)
            }

            ((TileAdapter) gridComputer.getAdapter()).notifyDataSetChanged();
            //((TextView) findViewById(R.id.playerText)).setText("Computer's Turn");
            //((ImageView) findViewById(R.id.playericon)).setImageResource(R.drawable.computericon);
            ((ImageView) findViewById(R.id.turn)).setImageResource(R.drawable.computerturn);
            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!victory) {
                        defeat = mGame.ComputerAndPlayerShoot();

                        if (defeat) {
                            gameOverScreen(DEFEAT);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((TileAdapter) gridPlayer.getAdapter()).notifyDataSetChanged();
                            //((ImageView) findViewById(R.id.playericon)).setImageResource(R.drawable.playericon);
                            //((TextView) findViewById(R.id.playerText)).setText("Player's Turn");
                            ((ImageView) findViewById(R.id.turn)).setImageResource(R.drawable.playerturn);
                            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
                            gridComputer.setEnabled(true);
                        }
                    });
                }
            });

            t.start();
        }
    };


    private void gameOverScreen(String winStatus) {



        boolean isInsert;

        double sco = ((RealPlayer)(mGame.getRealPlayer())).getNumOfTurns();
        sco = (7/sco)*1000;
        if ((winStatus.equalsIgnoreCase("victory"))){
            if(myDb.getScoreCount(passedString)>=10){
                if(sco >= myDb.getMinScore(passedString)){
                    getNameForHighScore(winStatus);
                }
            }
            else{
                getNameForHighScore(winStatus);
            }



        }
        else{
            goToActivity(winStatus);
        }


    }
    private void goToActivity(String winStatus){
        Intent intent = new Intent(gameActivity.this, gameOverActivity.class);
        Bundle b = new Bundle();

        b.putString(STRING_KEY, winStatus);

        intent.putExtra(BUNDLE_KEY, b);
        startActivity(intent);
        finish();
    }

    private String m_Text = "";
    private static String sta="";

    private void getNameForHighScore(String winStatus){
        sta = winStatus;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New High Score");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                double sco = ((RealPlayer)(mGame.getRealPlayer())).getNumOfTurns();
                sco = (7/sco)*1000;
                myDb.addProduct(m_Text,(int)sco, passedString);
                goToActivity(sta);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                goToActivity(sta);
            }
        });

        builder.show();
    }


    public void startStorm(){
        //iv = findViewById(R.id.stormiv);
        //iv.setBackgroundResource(R.drawable.wave_animation);
        //iv.setVisibility(View.VISIBLE);
        //stormAnim = (AnimationDrawable) iv.getBackground();
        //stormAnim.start();
        Log.i("Storm" , "before storm start");
        mGame.doTheStorm();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gridComputer.setAdapter(new TileAdapter(getApplicationContext(), mGame.getComputerBoard()));
                iv1.setBackgroundResource(R.drawable.wave_animation);
                stormAnim = (AnimationDrawable) iv1.getBackground();
                stormAnim.start();
                stormAnim.setOneShot(true);
            }
        });

        Log.i("Storm" , "storm start");
    }

    @Override
    public void StormIsComing() {
        Log.i("Storm" , "storm is coming");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iv1.setBackgroundResource(R.drawable.storm_animation);
                stormAnim = (AnimationDrawable) iv1.getBackground();
                iv1.setVisibility(View.VISIBLE);
                stormAnim.start();
            }
        });


    }

    @Override
    public void StopStorm() {
        Log.i("Storm" , "storm stop");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iv1.setVisibility(View.INVISIBLE);
                stormAnim.stop();
            }
        });

    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SensorService.MyBinder b = (SensorService.MyBinder) iBinder;
        sensorService = b.getService();
        sensorService.setCallbacks(gameActivity.this);
        sensorService.setStormInterfaceListener(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        sensorService = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent in = new Intent(getApplicationContext(),SensorService.class);
        bindService(in,this,Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }






}
