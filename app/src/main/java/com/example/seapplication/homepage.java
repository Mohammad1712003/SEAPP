package com.example.seapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
ListView lv;
Button Menu,Signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
        lv=(ListView) findViewById(R.id.listviewhome);
        setTitle("My Courses");

       Menu=findViewById(R.id.menu_button);
       Signout=findViewById(R.id.sign_out_button);




Database DB=new Database(homepage.this);
        ArrayList names=DB.getallcourses(DB.getcur_signed_in());
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this,  R.layout.activity_rowsforlistview, R.id.list_item,names);
lv.setAdapter(itemsAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if(position< names.size()){
                        DB.setcur_course_name(names.get(position).toString());
                    openCourseConetent(view);}
//                if(position==1)
//                    openGoals(view);
//                if(position==2)
//                    openStatuspage(view);
            }

        });


        Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            openMenupage(v);
            }});

        Signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              openSigninpage(v);

            }});
    }


    public void  openCourseConetent(View v){
        Intent i= new Intent(this, Course_Content.class);
        startActivity(i);
    }



    public void openMenupage(View v){
        Intent i= new Intent(this, Menu.class);

        startActivity(i);

    }

    public void openSigninpage(View v){
        Intent i= new Intent(this, MainActivity.class);

        startActivity(i);

    }
    }