package com.example.seapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addnewgoal extends AppCompatActivity {
    Button add;
    EditText goal_chapter, CNAME, nbofhours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewgoal);
        setTitle("Add New Goal");
        add = findViewById(R.id.add1);
        goal_chapter = findViewById(R.id.goal_chapter);
        CNAME = findViewById(R.id.CNAME);
        nbofhours = findViewById(R.id.nbofhours);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Database DB = new Database(addnewgoal.this);
                if (!goal_chapter.getText().toString().isEmpty() && !CNAME.getText().toString().isEmpty() && !nbofhours.getText().toString().isEmpty()) {
                    int nb = 0;
                    try {
                        nb = Integer.parseInt(nbofhours.getText().toString());
                        GOAL_CLASS g_class = new GOAL_CLASS(goal_chapter.getText().toString(), CNAME.getText().toString(), nb);
                        DB.add_new_goal(g_class);
                        Toast.makeText(addnewgoal.this, "Added new Goal", Toast.LENGTH_SHORT).show();
                        openGoals(v);
                    } catch (NumberFormatException e) {
                        nb = -1;
                        Toast.makeText(addnewgoal.this, "Error in nb of hours", Toast.LENGTH_SHORT).show();
                        GOAL_CLASS g_class = new GOAL_CLASS("-1", "-1", -1);
                    }

                } else {
                    Toast.makeText(addnewgoal.this, "Error", Toast.LENGTH_SHORT).show();
                    GOAL_CLASS g_class = new GOAL_CLASS("-1", "-1", -1);
                }
            }
        });


    }

    public void openGoals(View v) {
        Intent i = new Intent(this, Goals.class);

        startActivity(i);

    }
}