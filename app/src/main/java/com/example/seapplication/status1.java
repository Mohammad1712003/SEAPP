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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class status1 extends AppCompatActivity {
    Button startStopButton, pauseResumeButton;
    ProgressBar specified, specified1, average, average1;
    TextView timerText, specifiedText, averageText;
    Timer timer;
    float req;
    String result;
    TimerTask timerTask;
    Double time =0.0;
    int hoursT=0, minT=0,hoursT1=0, minT1=0;
    long startTime;
    long endTime;
    float av;
    boolean timerStarted = false, pause=false;
    int minutes, hours;
    Database db = new Database(status1.this);
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
        specified1 = findViewById(R.id.specified1);
        average1 = findViewById(R.id.average1);
        timer = new Timer();
        specifiedText = (TextView) findViewById(R.id.specified_viewProgress);
        averageText = (TextView) findViewById(R.id.average_viewProgress);

        specified.setProgress(0);
        specified1.setProgress(0);
        average.setProgress(0);
        average1.setProgress(0);

        req = db.GetnbofRequiredstudyHours(db.getcur_chapter());

        String doubleAsString = String.valueOf(req);
        int indexOfDecimal = doubleAsString.indexOf(".");
        Random rd = new Random(); // creating Random object
        av=rd.nextFloat();
        result = String.format("%.2f", av);
        specifiedText.setText("/0"+doubleAsString.substring(0, indexOfDecimal)+":"+doubleAsString.substring(indexOfDecimal+1)+"0");
        averageText.setText("/"+result);

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
                        hoursT+=hours;
                        minT+=minutes;
//                        specifiedText.setText(hoursT+"."+minT+"/"+db.GetnbofRequiredstudyHours(db.getcur_chapter()));
                        String studied;
                        if(minT<10)
                            studied = hoursT+".0"+minT;
                        else
                            studied = hoursT+"."+minT;
                        float x = Float.parseFloat(studied);
                        if(x<100) {
                            specified.setProgress((int) ((x / db.GetnbofRequiredstudyHours(db.getcur_chapter())) * 100));
                            average.setProgress((int) ((x / av) * 100));
                        }
                        else{
                            specified.setProgress(100);
                            specified1.setProgress(100-((int)((x/db.GetnbofRequiredstudyHours(db.getcur_chapter()))*100)));
                            average.setProgress(100);
                            average1.setProgress((int) ((x / av) * 100));
                        }
                        if(minT<10) {
                            req = db.GetnbofRequiredstudyHours(db.getcur_chapter());
                            String doubleAsString = String.valueOf(req);
                            int indexOfDecimal = doubleAsString.indexOf(".");
                            specifiedText.setText("0" + hoursT + ":0" + minT + "/0"+doubleAsString.substring(0, indexOfDecimal)+":"+doubleAsString.substring(indexOfDecimal+1)+"0");
                            averageText.setText("0" + hoursT + ":0" + minT + "/"+result);
                        }
                        else {
                            req = db.GetnbofRequiredstudyHours(db.getcur_chapter());
                            String doubleAsString = String.valueOf(req);
                            int indexOfDecimal = doubleAsString.indexOf(".");
                            specifiedText.setText("0" + hoursT + ":" + minT + "/0"+doubleAsString.substring(0, indexOfDecimal)+":"+doubleAsString.substring(indexOfDecimal+1)+"0");
                            averageText.setText("0" + hoursT + ":" + minT + "/0"+result);
                        }
//                        hoursT=0;
//                        minT=0;
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
        minutes=hours=0;
        int rounded = (int) Math.round(time);
        int seconds =( (rounded%86400)%3600)%60;
        minutes =( (rounded%86400)%3600)/60;
        hours =( (rounded%86400)/3600);
        return formatTimer(seconds, minutes, hours);
    }

//    public String getTimerTextFormatted(){
//        int rounded = (int) Math.round(time);
////        int seconds =( (rounded%86400)%3600)%60;
//        int minutes =( (rounded%86400)%3600)/60;
//        int hours =( (rounded%86400)/3600);
//        return hours+"."+minutes;
//    }


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