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
        super(context, "SE___DB.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createACC_Table = "CREATE TABLE " + ACCOUNTS_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT UNIQUE, " + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createACC_Table);

        String createCOURSES_Table = "CREATE TABLE " + COURSES_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + C_NAME + " TEXT UNIQUE, " + C_TITLE + " TEXT, " + C_SECTION + " TEXT," + I_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createCOURSES_Table);

        String createAcc_Takes_Courses_Table = "CREATE TABLE ACC_TAKES_COURSES_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + PASSWORD + " TEXT, "+C_NAME + " TEXT UNIQUE, " + C_TITLE + " TEXT, " + C_SECTION + " TEXT, " + I_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createAcc_Takes_Courses_Table);

        String createGoals_Table = "CREATE TABLE " + GOALS_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + GOAL_NAME + " TEXT, " + COURSE_NAME + " TEXT, " + DURATION + " INTEGER)";
        sqLiteDatabase.execSQL(createGoals_Table);

        String createChapters_Table = "CREATE TABLE " + CHAPTERS_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CHAPTER_NAME + " TEXT UNIQUE, " + REQUIRED_NB_OF_HOURS + " FLOAT, " + NB_OF_STUDIED_HOURS + " FLOAT)";
        sqLiteDatabase.execSQL(createChapters_Table);

        String createCOURSES_HAS_CHAPTER_Table = "CREATE TABLE " + COURSE_HAS_CHAPTER_TABLE + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + C_NAME + " TEXT, " + C_TITLE + " TEXT, " + C_SECTION + " TEXT," + I_NAME + " TEXT, " + CHAPTER_NAME + " TEXT, " + REQUIRED_NB_OF_HOURS + " FLOAT, " + NB_OF_STUDIED_HOURS + " FLOAT)";
        sqLiteDatabase.execSQL(createCOURSES_HAS_CHAPTER_Table);

        String createcurr_Table = "CREATE TABLE CUR_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, CUR_COURSE_NAME TEXT)";
        sqLiteDatabase.execSQL(createcurr_Table);

        String createcurrSignedin_Table = "CREATE TABLE CUR_SIGNED_IN_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT)";
        sqLiteDatabase.execSQL(createcurrSignedin_Table);

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

    //CHPATERS TABLE
    public static final String CHAPTERS_TABLE = "CHAPTERS_TABLE";
    public static final String CHAPTER_NAME = "CHAPTER_NAME";
    public static final String REQUIRED_NB_OF_HOURS = "REQUIRED_NB_OF_HOURS";
    public static final String NB_OF_STUDIED_HOURS = "NB_OF_STUDIED_HOURS";

    //CHPATERS TABLE
    public static final String COURSE_HAS_CHAPTER_TABLE = "COURSE_HAS_CHAPTER_TABLE";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // INITIALIZER(add some data at the beg of the app)
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
        cv_main2.put(GOAL_NAME, "ch9 sec3");
        cv_main2.put(COURSE_NAME, "CSC490");
        cv_main2.put(DURATION, 1);
        insert = db_main.insert(GOALS_TABLE, null, cv_main2);
        //*********************************************************************************//

        //ADD SOME CHAPTERS TO THE DB

        ContentValues cv_main3 = new ContentValues();
        cv_main3.put("ID", 1);
        cv_main3.put(CHAPTER_NAME, "CH1 SEC1 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.0);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 2);
        cv_main3.put(CHAPTER_NAME, "CH1 SEC2 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.5);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 3);
        cv_main3.put(CHAPTER_NAME, "CH2 SEC1 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.1);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 4);
        cv_main3.put(CHAPTER_NAME, "CH2 SEC2 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.6);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 5);
        cv_main3.put(CHAPTER_NAME, "CH3 SEC1+SEC2 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 2.0);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 6);
        cv_main3.put(CHAPTER_NAME, "CH7 SEC1 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.3);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 7);
        cv_main3.put(CHAPTER_NAME, "CH8 SEC1+SEC2 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 2.0);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 8);
        cv_main3.put(CHAPTER_NAME, "CH10 FULL ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.2);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        cv_main3.put("ID", 9);
        cv_main3.put(CHAPTER_NAME, "CH20 FULL ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.0);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);


        cv_main3.put("ID", 10);
        cv_main3.put(CHAPTER_NAME, "CH31 SEC1 ");
        cv_main3.put(REQUIRED_NB_OF_HOURS, 1.3);
        cv_main3.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(CHAPTERS_TABLE, null, cv_main3);

        //Course_has_Chapters

        ContentValues cv_main4 = new ContentValues();
        cv_main4.put(C_NAME, "CSC490");
        cv_main4.put(C_TITLE, "SOftware Engineering");
        cv_main4.put(C_SECTION, "12");
        cv_main4.put(I_NAME, "Khaleel Mershad");
        cv_main4.put("ID", 1);
        cv_main4.put(CHAPTER_NAME, "CH1 SEC1 ");
        cv_main4.put(REQUIRED_NB_OF_HOURS, 1.0);
        cv_main4.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(COURSE_HAS_CHAPTER_TABLE, null, cv_main4);

        cv_main4.put(C_NAME, "CSC490");
        cv_main4.put(C_TITLE, "SOftware Engineering");
        cv_main4.put(C_SECTION, "12");
        cv_main4.put(I_NAME, "Khaleel Mershad");
        cv_main4.put("ID", 2);
        cv_main4.put(CHAPTER_NAME, "CH1 SEC2 ");
        cv_main4.put(REQUIRED_NB_OF_HOURS, 1.5);
        cv_main4.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(COURSE_HAS_CHAPTER_TABLE, null, cv_main4);

        cv_main4.put(C_NAME, "CSC490");
        cv_main4.put(C_TITLE, "SOftware Engineering");
        cv_main4.put(C_SECTION, "12");
        cv_main4.put(I_NAME, "Khaleel Mershad");
        cv_main4.put("ID", 3);
        cv_main4.put(CHAPTER_NAME, "CH20 FULL ");
        cv_main4.put(REQUIRED_NB_OF_HOURS, 1.0);
        cv_main4.put(NB_OF_STUDIED_HOURS, 0.0);
        insert = db_main.insert(COURSE_HAS_CHAPTER_TABLE, null, cv_main4);


        COURSE_HAS_CHAPTER(new Course(4, "CSC458", "Game Programming", "15", " Hussein Bakri"),
                new Chapter("CH20 FULL ", 1.0f, 0.0f));
        COURSE_HAS_CHAPTER(new Course(5, "CSC458", "Game Programming", "15", " Hussein Bakri"),
                new Chapter("CH8 SEC1+SEC2 ", 2.0f, 0.0f));
        COURSE_HAS_CHAPTER(new Course(6, "CSC458", "Game Programming", "15", " Hussein Bakri"),
                new Chapter("CH2 SEC1 ", 1.1f, 0.0f));
        COURSE_HAS_CHAPTER(new Course(7, "COM203", "Communication and Speeches", "20", "Walaa Harb"),
                new Chapter("CH7 SEC1 ", 1.3f, 0.0f));
        COURSE_HAS_CHAPTER(new Course(8, "COM203", "Communication and Speeches", "20", "Walaa Harb"),
                new Chapter("CH3 SEC1+SEC2 ", 2.0f, 0.0f));

        STUDENT_TAKES_COURSE(new Course(1, "CSC458", "Game Programming", "15", " Hussein Bakri"), new ACCOUNTS("John123", "johnsmith"));
        STUDENT_TAKES_COURSE(new Course(2, "CSC447", "Parallel course", "10", " Karim Jahed"), new ACCOUNTS("Johan123", "johnsmith"));
        STUDENT_TAKES_COURSE(new Course(3, "CSC490", "SOftware Engineering", "12", "Khaleel Mershad"), new ACCOUNTS("John123", "johnsmith"));
        STUDENT_TAKES_COURSE(new Course(4, "COM203", "Communication and Speeches", "20", "Walaa Harb"), new ACCOUNTS("Ema16", "16_8_2001"));
        STUDENT_TAKES_COURSE(new Course(5, "ENG202", "Advanced English", "22", "Randa keidy"), new ACCOUNTS("Ema16", "16_8_2001"));



    }

    public void setcur_course_name(String c_name) {
        SQLiteDatabase db_main = this.getWritableDatabase();
        ContentValues cv_main = new ContentValues();
        cv_main.put("CUR_COURSE_NAME", c_name);
        long insert = db_main.insert("CUR_TABLE", null, cv_main);
    }

    public String getcur_course_name() {

        String queryString = "SELECT * FROM CUR_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        String cur_name = "";
        if (cursor.moveToFirst()) {
            do {
                cur_name = cursor.getString(1);
                i++;
            } while (cursor.moveToNext());
        }
        return cur_name;
    }

    public void setcur_signed_in(String name) {
        SQLiteDatabase db_main = this.getWritableDatabase();
        ContentValues cv_main = new ContentValues();
        cv_main.put("USERNAME", name);
        long insert = db_main.insert("CUR_SIGNED_IN_TABLE", null, cv_main);
    }

    public String getcur_signed_in() {

        String queryString = "SELECT * FROM CUR_SIGNED_IN_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        String cur_username = "";
        if (cursor.moveToFirst()) {
            do {
                cur_username = cursor.getString(1);
                i++;
            } while (cursor.moveToNext());
        }
        return cur_username;
    }

    public ArrayList<String> getallcourses(String username) {
        ArrayList<String> name = new ArrayList<String>();
        String queryString = "SELECT * FROM ACC_TAKES_COURSES_TABLE WHERE USERNAME= '"+username+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {

                String cur_name = cursor.getString(3);
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
                goal.add(new GOAL_CLASS(cur_name, cur_c_name, cur_duration));

                i++;
            } while (cursor.moveToNext());
        }
        return goal;
    }

    public ArrayList<String> getcourse_chapters() {
        String coursename = getcur_course_name();
        ArrayList<String> list = new ArrayList<String>();
        String queryString = "SELECT * FROM " + COURSE_HAS_CHAPTER_TABLE + " WHERE C_NAME='" + coursename + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {

                String chapter_name = cursor.getString(5);
                list.add(chapter_name);
                i++;
            } while (cursor.moveToNext());
        }
        return list;
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

    public boolean AccomplishGoal(String toString) {

        ArrayList<GOAL_CLASS> arrayList = getallGoals();
        GOAL_CLASS g;
        int index = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).toString().equals(toString)) {
                index = i;
                break;
            }
        }
        /*
        SQLiteDatabase db=this.getWritableDatabase();
        String qureyString="DELETE FROM "+GOALS_TABLE+ " WHERE "+GOAL_NAME + " = "+ arrayList.get(index).getGOAL();
                //+" and "+COURSE_NAME+"="+arrayList.get(index).getCourse() +" and "+DURATION+"="+arrayList.get(index).getDuration();
        Cursor cursor=db.rawQuery(qureyString,null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
        */
        /////////////////

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvcc = new ContentValues();
        index++;
        long del = db.delete(GOALS_TABLE, "ID=?", new String[]{index + ""});

        if (del == -1)
            return false;
        else
            return true;

    }


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


    public void COURSE_HAS_CHAPTER(Course course, Chapter chapter) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // make sure that the course inputted exists in th DB
        String queryString = "SELECT * FROM " + COURSES_TABLE;
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(queryString, null);


        String course_name = "";
        if (cursor.moveToFirst()) {
            do {
                course_name = cursor.getString(1);

                if (course_name.equalsIgnoreCase(course.getC_NAME())) {
                    break;
                }
            } while (cursor.moveToNext());
        }

        // make sure that the chapter inputted exists in the DB
        String queryString2 = "SELECT * FROM " + CHAPTERS_TABLE;
        SQLiteDatabase DBB = this.getReadableDatabase();
        Cursor cursor2 = DBB.rawQuery(queryString2, null);

        String chapter_name = "";

        if (cursor2.moveToFirst()) {
            do {
                chapter_name = cursor2.getString(1);
                if (chapter_name.equalsIgnoreCase(chapter.getCHAPTER_NAME())) {
                    break;
                }

            } while (cursor2.moveToNext());
        }
        if ((course_name.equalsIgnoreCase(course.getC_NAME())) && chapter_name.equalsIgnoreCase(chapter.getCHAPTER_NAME())) {
            Cursor cursor3 = DB.rawQuery(queryString2, null);
            ContentValues cvvv = new ContentValues();


            ContentValues cvv = new ContentValues();

            cvv.put("ID", course.getID());
            cvv.put(C_NAME, course.getC_NAME());
            cvv.put(C_TITLE, course.getC_TITLE());
            cvv.put(C_SECTION, course.getC_SECTION());
            cvv.put(I_NAME, course.getI_NAME());
            cvv.put(CHAPTER_NAME, chapter.getCHAPTER_NAME());
            cvv.put(REQUIRED_NB_OF_HOURS, chapter.getREQUIRED_NB_OF_HOURS());
            cvv.put(NB_OF_STUDIED_HOURS, chapter.getNB_OF_STUDIED_HOURS());

            db.insert(COURSE_HAS_CHAPTER_TABLE, null, cvv);

        }

    }
    public void STUDENT_TAKES_COURSE(Course course, ACCOUNTS account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // make sure that the course inputted exists in th DB
        String queryString = "SELECT * FROM " + COURSES_TABLE;
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery(queryString, null);


        String course_name = "";
        if (cursor.moveToFirst()) {
            do {
                course_name = cursor.getString(1);

                if (course_name.equalsIgnoreCase(course.getC_NAME())) {
                    break;
                }
            } while (cursor.moveToNext());
        }

        // make sure that the chapter inputted exists in the DB
        String queryString2 = "SELECT * FROM " + ACCOUNTS_TABLE;
        SQLiteDatabase DBB = this.getReadableDatabase();
        Cursor cursor2 = DBB.rawQuery(queryString2, null);

        String Account = "";

        if (cursor2.moveToFirst()) {
            do {
                Account = cursor2.getString(1);
                if (Account.equalsIgnoreCase(account.getUSERNAME())) {
                    break;
                }

            } while (cursor2.moveToNext());
        }
        if ((course_name.equalsIgnoreCase(course.getC_NAME())) && Account.equalsIgnoreCase(account.getUSERNAME())) {
            Cursor cursor3 = DB.rawQuery(queryString2, null);
            ContentValues cvvv = new ContentValues();


            ContentValues cvv = new ContentValues();

            cvv.put("ID", course.getID());
            cvv.put(C_NAME, course.getC_NAME());
            cvv.put(C_TITLE, course.getC_TITLE());
            cvv.put(C_SECTION, course.getC_SECTION());
            cvv.put(I_NAME, course.getI_NAME());
            cvv.put(USERNAME, account.getUSERNAME());
            cvv.put(PASSWORD, account.getPASSWORD());


            db.insert("ACC_TAKES_COURSES_TABLE", null, cvv);

        }
    }


}


