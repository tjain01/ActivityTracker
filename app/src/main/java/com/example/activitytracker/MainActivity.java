package com.example.activitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView day,month,year,time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        month = (TextView)findViewById(R.id.month);
        day = (TextView)findViewById(R.id.day);
        year = (TextView)findViewById(R.id.year);
        time = (TextView)findViewById(R.id.time);


        //getting current date and time

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

        String[] splitDate = formattedDate.split(",");

        month.setText(splitDate[1]);
        day.setText(splitDate[0]);
        year.setText(splitDate[2]);

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            long date = System.currentTimeMillis();
                                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss a");
                                String currentDateTimeString = sdf.format(date);
                                time.setText(currentDateTimeString);

                            }
                        });
                    }
                }
                catch (InterruptedException e){}
            }
        };
        t.start();



    }
}
