package com.example.seapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Menu extends AppCompatActivity {
ListView lv;
String[] Menu={"My Courses","My Goals","My Status"};
Button Signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu");
        lv=(ListView) findViewById(R.id.listviewmenu);
        Signout=findViewById(R.id.sign_out_button4);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this,  R.layout.activity_rowsforlistview,  R.id.list_item,Menu);
        lv.setAdapter(itemsAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                  openhomepage(view);
                if(position==1)
                    openGoals(view);
            }

        });

        Signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSigninpage(v);

            }});
    }


    public void openhomepage(View v){
        Intent i= new Intent(this, homepage.class);

        startActivity(i);

    }
    public void openGoals(View v){
        Intent i= new Intent(this, Goals.class);

        startActivity(i);

    }
    public void openSigninpage(View v){
        Intent i= new Intent(this, MainActivity.class);

        startActivity(i);

    }



}