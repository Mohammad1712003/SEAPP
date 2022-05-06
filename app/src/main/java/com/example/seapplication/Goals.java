package com.example.seapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Goals extends AppCompatActivity {
    FloatingActionButton add_new_goal;
    ListView lv;
    Button Menu, Signout;
    int i=0;
    TextView alertview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        setTitle("My Goals");


        Menu = findViewById(R.id.menu_button2);
        Signout = findViewById(R.id.sign_out_button2);
        add_new_goal = findViewById(R.id.addnewgoal);
       // alertview=(TextView) findViewById(R.id.Alert_to_delete);
        lv = (ListView) findViewById(R.id.goalslistview1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        Database DB = new Database(Goals.this);
        ArrayList<GOAL_CLASS> goalss = DB.getallGoals();
        ArrayList<String> goalstostring=new ArrayList<String>();

        for(int i=0;i<goalss.size();i++){
            goalstostring.add(i,goalss.get(i).toString());
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.activity_rowsforlistview, R.id.list_item, goalstostring);
        lv.setAdapter(itemsAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              int index=position;
                i++;
                //oast.makeText(Goals.this, goalss.get(index)+ " ", Toast.LENGTH_SHORT).show();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(i==1){
                            Toast.makeText(Goals.this, "Pressed once", Toast.LENGTH_SHORT).show();
                        }
                        else if(i==2){
                            Toast.makeText(Goals.this, "twice", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder=new AlertDialog.Builder(Goals.this);
                            builder.setCancelable(true);
                            builder.setTitle("Did you Accomplish this Goal?");
                            builder.setMessage(goalss.get(index)+"");

                            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   // alertview.setVisibility(View.VISIBLE);
                                    Toast.makeText(Goals.this, "YAYYY", Toast.LENGTH_SHORT).show();

                                }
                            });
                            builder.show();
                        }
                        i=0;
                    }
                },300);

            }

        });

        Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // openMenupage(v);


            }
        });

        Signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSigninpage(v);

            }
        });

        add_new_goal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //********** //////////Notification//////////*********
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Goals.this, "My Notification");
                builder.setContentTitle("My Title");
                builder.setContentTitle("Hello from easy body,");
                builder.setSmallIcon(R.drawable.goal);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Goals.this);
                managerCompat.notify(1, builder.build());
                /////////////////////**************************************////////

                openADDnewGOAL(v);

            }
        });

    }


    public void openADDnewGOAL(View v) {

        Intent i = new Intent(this, addnewgoal.class);

        startActivity(i);

    }


    public void openMenupage(View v) {
        Intent i = new Intent(this, Menu.class);

        startActivity(i);

    }

    public void openSigninpage(View v) {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);

    }

}