package com.example.seapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.mbms.MbmsErrors;


import androidx.annotation.Nullable;

import java.sql.Connection;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "S_E_D_B.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createACC_Table = "CREATE TABLE " + ACCOUNTS_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT UNIQUE, " + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createACC_Table);

        String createCOURSES_Table = "CREATE TABLE " + COURSES_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + C_NAME + " TEXT UNIQUE, " + C_TITLE + " TEXT, " + C_SECTION + " TEXT," + I_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createCOURSES_Table);

        String createGoals_Table = "CREATE TABLE " + GOALS_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + GOAL_NAME + " TEXT, " + COURSE_NAME + " TEXT, " + DURATION + " INTEGER)";
        sqLiteDatabase.execSQL(createGoals_Table);

    }

    //ACC TABLE
    public static final String ACCOUNTS_TABLE = "ACCOUNTS_TABLE";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    //COURSES TABLE
    public static final String COURSES_TABLE = "COURSES_TABLE";
    public static final String C_NAME = "C_NAME";
    public static final String C_TITLE = "C_TITLE";
    public static final String C_SECTION = "C_SECTION";
    public static final String I_NAME = "I_NAME";
    public static final String HOURS_OF_STUDY = "HOURS_OF_STUDY";

    //GOALS TABLE
    public static final String GOALS_TABLE = "GOALS_TABLE";
    public static final String GOAL_NAME = "GOAL_NAME";
    public static final String COURSE_NAME = "COURSE_NAME";
    public static final String DURATION = "DURATION";


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Initializer() {
        //add some accounts to the database when  creating the app
        SQLiteDatabase db_main = this.getWritableDatabase();
        ContentValues cv_main = new ContentValues();

        cv_main.put(USERNAME, "John123");
        cv_main.put(PASSWORD, "johnsmith");
        long insert = db_main.insert(ACCOUNTS_TABLE, null, cv_main);

        cv_main.put(USERNAME, "Ema16");
        cv_main.put(PASSWORD, "16_8_2001");
        insert = db_main.insert(ACCOUNTS_TABLE, null, cv_main);
//****************************************************************************************//

        //add some courses to the Database when  creating the app
        //(happens only when opening the app for the first time)
        ContentValues cv_main1 = new ContentValues();
        cv_main1.put(C_NAME, "CSC490");
        cv_main1.put(C_TITLE, "SOftware Engineering");
        cv_main1.put(C_SECTION, "12");
        cv_main1.put(I_NAME, "Khaleel Mershad");
        insert = db_main.insert(COURSES_TABLE, null, cv_main1);

        cv_main1.put(C_NAME, "CSC447");
        cv_main1.put(C_TITLE, "Parallel course");
        cv_main1.put(C_SECTION, "10");
        cv_main1.put(I_NAME, " Karim Jahed");
        insert = db_main.insert(COURSES_TABLE, null, cv_main1);

        cv_main1.put(C_NAME, "CSC458");
        cv_main1.put(C_TITLE, "Game Programming");
        cv_main1.put(C_SECTION, "15");
        cv_main1.put(I_NAME, " Hussein Bakri");
        insert = db_main.insert(COURSES_TABLE, null, cv_main1);

        cv_main1.put(C_NAME, "ENG202");
        cv_main1.put(C_TITLE, "Advanced English");
        cv_main1.put(C_SECTION, "22");
        cv_main1.put(I_NAME, "Randa keidy");
        insert = db_main.insert(COURSES_TABLE, null, cv_main1);

        cv_main1.put(C_NAME, "COM203");
        cv_main1.put(C_TITLE, "Communication and Speeches");
        cv_main1.put(C_SECTION, "20");
        cv_main1.put(I_NAME, "Walaa Harb");
        insert = db_main.insert(COURSES_TABLE, null, cv_main1);
        //*********************************************************************************//

        //ADD SOME GOALS TO ACCOMPLISH

        ContentValues cv_main2 = new ContentValues();
        cv_main2.put("ID", 1);
        cv_main2.put(GOAL_NAME, "ch1");
        cv_main2.put(COURSE_NAME, "CSC490");
        cv_main2.put(DURATION, 1);
        insert = db_main.insert(GOALS_TABLE, null, cv_main2);

        cv_main2.put("ID", 2);
        cv_main2.put(GOAL_NAME, "ch5 sect1");
        cv_main2.put(COURSE_NAME, "CSC458");
        cv_main2.put(DURATION, 2);

        insert = db_main.insert(GOALS_TABLE, null, cv_main2);

        cv_main2.put("ID", 3);
        cv_main2.put(GOAL_NAME, "ch2");
        cv_main2.put(COURSE_NAME, "CSC490");
        cv_main2.put(DURATION, 4);

        insert = db_main.insert(GOALS_TABLE, null, cv_main2);
        cv_main2.put("ID", 4);
        cv_main2.put(GOAL_NAME, "ch8 sec3");
        cv_main2.put(COURSE_NAME, "CSC447");
        cv_main2.put(DURATION, 3);
        insert = db_main.insert(GOALS_TABLE, null, cv_main2);

        cv_main2.put("ID", 5);
        cv_main2.put(GOAL_NAME, "ch8 sec3");
        cv_main2.put(COURSE_NAME, "CSC447");
        cv_main2.put(DURATION, 3);
        insert = db_main.insert(GOALS_TABLE, null, cv_main2);

    }

    public ArrayList<String> getallcourses() {
        ArrayList<String> name = new ArrayList<String>();
        String queryString = "SELECT * FROM " + COURSES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {

                String cur_name = cursor.getString(1);
                name.add(cur_name);
                i++;
            } while (cursor.moveToNext());
        }
        return name;
    }

    public ArrayList<GOAL_CLASS> getallGoals() {
        ArrayList<GOAL_CLASS> goal = new ArrayList<GOAL_CLASS>();
        String queryString = "SELECT * FROM " + GOALS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {

                String cur_name = cursor.getString(1);
                String cur_c_name = cursor.getString(2);
                int cur_duration = Integer.parseInt(cursor.getString(3));

                GOAL_CLASS g = new GOAL_CLASS(cur_name, cur_c_name, cur_duration);
                goal.add(new GOAL_CLASS(cur_name,cur_c_name,cur_duration));

                i++;
            } while (cursor.moveToNext());
        }
        return goal;
    }


    public boolean add_new_goal(GOAL_CLASS goal) {
        SQLiteDatabase db_main = this.getWritableDatabase();
        ContentValues cv_main = new ContentValues();
        if (goal.getDuration() == -1)
            return false;
        cv_main.put(GOAL_NAME, goal.getGOAL());
        cv_main.put(COURSE_NAME, goal.getCourse());
        cv_main.put(DURATION, goal.getDuration());
        long insert = db_main.insert(GOALS_TABLE, null, cv_main);
        return true;
    }
/*
    public boolean AccomplishGoal( String toString){

        ArrayList<String> arrayList=getallGoals();
        GOAL_CLASS g;
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).equals(toString) {

                break;
            }

        }
        SQLiteDatabase db=this.getWritableDatabase();
        String qureyString="DELETE FROM "+GOALS_TABLE+ " WHERE "+COLUMN_ID + "="+ products.getId();
        Cursor cursor=db.rawQuery(qureyString,null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }
*/

    public boolean CHECK_IF_ACC_EXISTS(ACCOUNTS acc) {

        //now checking if the inputed acc exists
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String queryString = "SELECT * FROM " + ACCOUNTS_TABLE;
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(queryString, null);


        String curr_username = "";
        String curr_password = "";


        if (cursor.moveToFirst()) {
            do {

                curr_username = cursor.getString(1);
                curr_password = cursor.getString(2);


                if (curr_username.equals(acc.getUSERNAME()) && curr_password.equals(acc.getPASSWORD()))
                    return true;
            } while (cursor.moveToNext());
        }


        return false;

    }
}


