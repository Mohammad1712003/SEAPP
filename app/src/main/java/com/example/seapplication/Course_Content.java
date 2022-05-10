package com.example.seapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Course_Content extends AppCompatActivity {
    ListView lv;
    Button Menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
        Database DB = new Database(Course_Content.this);
        setTitle(DB.getcur_course_name());
        lv=(ListView) findViewById(R.id.chapters_list_view);
        Menu=findViewById(R.id.menu_button4);

       ArrayList chapters=DB.getcourse_chapters();
           ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this,  R.layout.activity_rowsforlistview,  R.id.list_item,chapters);
        lv.setAdapter(itemsAdapter);
        if(chapters.isEmpty()){
            chapters.add("The instructor of "+DB.getcur_course_name()+" has not added any content yet");
        }

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String starting_letter= chapters.get(0).toString();
                if(!(starting_letter.charAt(0) =='T') && !(starting_letter.charAt(1)=='h') && !(starting_letter.charAt(2)=='e'))
                {
                    if(position<chapters.size()){
                        DB.setcur_chapter(chapters.get(position).toString());
                        openStatus(view);
                    }
                }

            }

        });





        Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 openMenupage(v);


            }
        });
    }


    public void openSigninpage(View v){
        Intent i= new Intent(this, MainActivity.class);

        startActivity(i);

    }

    public void openStatus(View v){
        Intent i= new Intent(this, status1.class);
        startActivity(i);

    }
    public void openMenupage(View v) {
        Intent i = new Intent(this, Menu.class);

        startActivity(i);

    }

}