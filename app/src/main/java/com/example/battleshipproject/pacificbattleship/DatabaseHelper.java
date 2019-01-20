package com.example.battleshipproject.pacificbattleship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String EASY_HIGH_SCORE = "easy_highscore_TEST";
    public static final String MED_HIGH_SCORE = "medium_highscore_TEST";
    public static final String HARD_HIGH_SCORE = "hard_highscore_TEST";

    public static final String TABLE_NAME_EASY = "HighScoreE_table";
    public static final String TABLE_NAME_MEDIUM = "HighScoreM_table";
    public static final String TABLE_NAME_HARD = "HighScoreH_table";




    public static final String DATABASE_NAME = "HighScore4.db";
    public static String TABLE_NAME = "HighScoreE_table";


    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SCORE";
    public static final String COL_4 = "TOW_SCORE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SCORE INTEGER,TOW_SCORE INTEGER)");

        db.execSQL("create table " + TABLE_NAME_EASY +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SCORE INTEGER,TOW_SCORE INTEGER)");
        db.execSQL("create table " + TABLE_NAME_MEDIUM +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SCORE INTEGER,TOW_SCORE INTEGER)");
        db.execSQL("create table " + TABLE_NAME_HARD +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SCORE INTEGER,TOW_SCORE INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String score,String score2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,score);
        contentValues.put(COL_4,score2);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public void addProduct(String name,int score, String difficultyLvl) {
        SQLiteDatabase db = this.getWritableDatabase();
       // int a=getMinScore();
       // int b =a;
        try {
            db.beginTransaction();
            try {
                switch (difficultyLvl){
                    case "Easy":
                        TABLE_NAME =TABLE_NAME_EASY;
                        break;
                    case "Medium":
                        TABLE_NAME =TABLE_NAME_MEDIUM;
                        break;
                    case "Hard":
                        TABLE_NAME =TABLE_NAME_HARD;
                        break;
                }

                ContentValues values = new ContentValues();
                values.put(COL_2, name);
                values.put(COL_3, score);
                db.insert(TABLE_NAME, null, values);



                db.execSQL("DELETE FROM " +TABLE_NAME +" WHERE ID NOT IN"+
                                " (SELECT ID FROM " +TABLE_NAME +" ORDER BY SCORE DESC,ID DESC LIMIT 10)");

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        } finally {
            db.close();
        }
    }

    public Cursor getAllData(String difficultyLvl) {
        SQLiteDatabase db = this.getWritableDatabase();
        switch (difficultyLvl){
            case "Easy":
                TABLE_NAME =TABLE_NAME_EASY;
                break;
            case "Medium":
                TABLE_NAME =TABLE_NAME_MEDIUM;
                break;
            case "Hard":
                TABLE_NAME =TABLE_NAME_HARD;
                break;
        }

        Cursor res = db.rawQuery("SELECT * FROM "+  TABLE_NAME+  " ORDER BY SCORE DESC,ID DESC LIMIT 10",null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public int getMinScore(String diff){
        int minScore =0;
        ArrayList<Integer> scoreList = new ArrayList<>();
        scoreList = getAllScores(diff);
         if(scoreList.size()>0){
             minScore= (Collections.min(scoreList));
         }
        return minScore;

    }

    public ArrayList<Integer> getAllScores(String diff) {
        ArrayList<Integer> scoreList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = null;
        try {

            Cursor cursor = getAllData(diff);
            while (cursor.moveToNext()) {
                scoreList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_3))));

            }
        }catch(Exception ex){

        }
        return scoreList;
    }
    public int getScoreCount(String diff){
        ArrayList<Integer> scoreList = new ArrayList<>();
        scoreList = getAllScores(diff);
        return scoreList.size();

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}