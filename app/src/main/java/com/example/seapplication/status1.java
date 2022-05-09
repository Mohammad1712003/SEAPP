package com.example.seapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class status1 extends AppCompatActivity {
    Button startStopButton, pauseResumeButton;
    ProgressBar specified, average;
    TextView timerText;
    Timer timer;
    TimerTask timerTask;
    Double time =0.0;
    long startTime;
    long endTime;
    boolean timerStarted = false, pause=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status1);
        setTitle("My Status");

        startStopButton = findViewById(R.id.start);
        pauseResumeButton = findViewById(R.id.pause);
        timerText = (TextView) findViewById(R.id.TimerText);
        specified = findViewById(R.id.specified);
        average = findViewById(R.id.average);

        timer = new Timer();

    }

    public void startStopTapped(View view) {
        if (timerStarted==false){
            timerStarted=true;
            startStopButton.setText("Stop");
            startTimer();
        }
        else{
            AlertDialog.Builder stopAlert = new AlertDialog.Builder(this);
            stopAlert.setTitle("Stop Timer");
            stopAlert.setMessage("Are you sure you want to stop timer?");
            stopAlert.setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(timerTask!=null){
                        timerStarted=false;
                        timerTask.cancel();
                        startStopButton.setText("Start");
                        time=0.0;
                        timerText.setText(formatTimer(0, 0, 0));
                    }
                }
            });
            stopAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            stopAlert.show();

        }
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText() {
        int rounded = (int) Math.round(time);
        int seconds =( (rounded%86400)%3600)%60;
        int minutes =( (rounded%86400)%3600)/60;
        int hours =( (rounded%86400)/3600);
        return formatTimer(seconds, minutes, hours);
    }

    private String formatTimer(int seconds, int minutes, int hours) {
        return String.format("%02d",hours)+" : "+String.format("%02d",minutes)+" : "+String.format("%02d",seconds);
    }

    public void pauseResumeTapped(View view) {
        if(pause==false){
            pause=true;
            timerTask.cancel();
            pauseResumeButton.setText("Resume");
        }
        else{
            pause=false;
            pauseResumeButton.setText("Pause");
            startTimer();
        }
    }
}