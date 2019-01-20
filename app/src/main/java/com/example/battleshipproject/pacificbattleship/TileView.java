package com.example.battleshipproject.pacificbattleship;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TileView extends RelativeLayout {

    TextView text;
    ImageView iv;
    ImageView sh;
    AnimationDrawable adExplode;
    AnimationDrawable adWater;
    AnimationDrawable adflame;
    private final int DEAD_SHIP = 100;
    private final int MISS = -10;

    public TileView(Context context) {
        super(context);
        iv = new ImageView(context);
        sh = new ImageView(context);
        setGravity(Gravity.CENTER);
        setLayoutDirection(LAYOUT_DIRECTION_LTR);

        //addView(sh);
        addView(sh);
        addView(iv);



        //addView(text);


    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setStatus(int status, boolean isInvisable, int isHorizontal) {

        boolean deadAnimated = false;
        if (status == 0 || (status > 0 && isInvisable && status != DEAD_SHIP)) {
            setBackgroundColor(0x30ffffff);

        } else if (status == DEAD_SHIP) {

            if(!deadAnimated) {
                deadAnimated=true;
                iv.setBackgroundResource(R.drawable.animation_sunk);
                //sh.setBackgroundColor(0x30ffffff);
                adflame = (AnimationDrawable) iv.getBackground();
                adflame.start();
                adflame.setOneShot(true);

            }
            if(isHorizontal==1) {
                sh.setBackgroundResource(R.drawable.headvhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==2) {
                sh.setBackgroundResource(R.drawable.bodyvhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==3) {
                sh.setBackgroundResource(R.drawable.endvhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==-1) {
                sh.setBackgroundResource(R.drawable.headhhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==-2) {
                sh.setBackgroundResource(R.drawable.bodyhhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else {
                sh.setBackgroundResource(R.drawable.endhhit);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }


            //setBackgroundColor(Color.BLACK);
            //setBackgroundResource(R.drawable.headvhit);

        } else if (status > 0 && status != DEAD_SHIP) {
            if(isHorizontal==-1){
                iv.setBackgroundResource(R.drawable.headh);
                setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==-2){
                iv.setBackgroundResource(R.drawable.bodyh);
                setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==-3){
                iv.setBackgroundResource(R.drawable.endh);
                setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==1){
                iv.setBackgroundResource(R.drawable.headv);
                setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==2){
                iv.setBackgroundResource(R.drawable.bodyv);
                setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==3){
                iv.setBackgroundResource(R.drawable.endv);
                setBackgroundColor(0x30ffffff);
            }

            //setBackgroundColor(Color.BLACK);


        } else if (status == MISS) {

            iv.setBackgroundResource(R.drawable.animation_water);
            adWater = (AnimationDrawable) iv.getBackground();
            adWater.start();
            adWater.setOneShot(true);

        } else if (status < 0 && status != MISS) {

            iv.setBackgroundResource(R.drawable.animation);
            adExplode = (AnimationDrawable) iv.getBackground();
            adExplode.start();
            adExplode.setOneShot(true);

            if(isHorizontal==1) {
                sh.setBackgroundResource(R.drawable.headv);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==2) {
                sh.setBackgroundResource(R.drawable.bodyv);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==3) {
                sh.setBackgroundResource(R.drawable.endv);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if (isHorizontal==-1) {
                sh.setBackgroundResource(R.drawable.headh);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else if(isHorizontal==-2) {
                sh.setBackgroundResource(R.drawable.bodyh);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
            else {
                sh.setBackgroundResource(R.drawable.endh);
                //text.setBackgroundColor(0x30ffffff);
                //setBackgroundColor(0x30ffffff);
            }
        }
    }
}
