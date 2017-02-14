package com.health_hack.www.healthhack;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isSensorPresent = false;
    private int level3 = 0;//measures time of vigourous exercise
    private int level2 = 0;//measures time of normal exercise
    private int level1 = 0;//measures time of very low calorie burn
    private int date_temp;
    private double calories_burnt=0.0;
    private double total_claories_burnt=0.0;
    private double weight;
    Context context;
    MySQLiteAdapter mySQLiteAdapter;
    MySQLiteAdapterSign mySQLiteAdapterSign;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
        level1 = level2 = level3 =0;
        Toast.makeText(getApplicationContext(),"OnCreate Created..",Toast.LENGTH_SHORT).show();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorPresent = true;
            Toast.makeText(getApplicationContext(),"Sensor present",Toast.LENGTH_LONG).show();
        }
        else {
            isSensorPresent = false;
            Toast.makeText(getApplicationContext(),"Sensor absent",Toast.LENGTH_LONG).show();
        }

        GregorianCalendar gcalendar = new GregorianCalendar();
        int dt = gcalendar.get(Calendar.DATE);
        int mn = gcalendar.get(Calendar.MONTH)+1;
        int yr = gcalendar.get(Calendar.YEAR);
        date_temp = 100*(100*yr+mn)+dt;
        context = this;

        tv1 = (TextView) findViewById(R.id.calo);

        mySQLiteAdapter=new MySQLiteAdapter(context);
        SQLiteDatabase db=mySQLiteAdapter.mySQLiteOpenHelper.getWritableDatabase();

        mySQLiteAdapterSign = new MySQLiteAdapterSign(this);
        SQLiteDatabase db1 = mySQLiteAdapterSign.mySQLiteOpenHelperSign.getWritableDatabase();
        String[] search=mySQLiteAdapterSign.DisplayAllRecord().split("\n");
        String[] search1=search[search.length-1].split(" ");
        weight=Double.valueOf(search1[6]);

        String searchrecords=mySQLiteAdapter.SearchOneArgRecord(date_temp);

        if (searchrecords.isEmpty()) {
            tv1.setText("0 Calories");
            MyToastMessage.myMessage(context, "No record found...");
        } else {
            double cal = getCalories(searchrecords.split("\n")[0]);
            total_claories_burnt=cal;
            tv1.setText(String.valueOf(cal));
            //MyToastMessage.myMessage(context, searchrecords);
            //Toast.makeText(getApplicationContext(),searchrecords,Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(getApplicationContext(),"Pinaki: "+String.valueOf(weight),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 10.0 && event.values[0]<15.0 ) {
            level1++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Toast.makeText(getApplicationContext(),"Steps detected of type:1..",Toast.LENGTH_SHORT).show();
        }

        else if (event.values[0] > 15.0 && event.values[0]<20.0 ) {
            level2++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Toast.makeText(getApplicationContext(),"Steps detected of type:2..",Toast.LENGTH_SHORT).show();
        }

        else if (event.values[0] > 20.0 && event.values[0]<25.0 ) {
            level3++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Toast.makeText(getApplicationContext(),"Steps detected of type:3..",Toast.LENGTH_SHORT).show();
        }

        calories_burnt = 0.175*(7.0*level1 + 12.0*level2 + 13.0*level3)*weight/60.0;
        tv1.setText(String.valueOf(calories_burnt+total_claories_burnt)+" Calories");
        //Toast.makeText(getApplicationContext(),String.valueOf(mySQLiteAdapterSign.Weight),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start_exercise(View view) {
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        Toast.makeText(getApplicationContext(),"Sensor Started..",Toast.LENGTH_SHORT).show();
    }

    public int getDate(String str){
        return Integer.valueOf(str.split(" ")[0]);

    }

    public double getCalories(String str) {
        return Double.valueOf(str.split(" ")[1]);
    }


    public void stop_exercise(View view) {
        sensorManager.unregisterListener(this);
        Toast.makeText(getApplicationContext(),"Sensor Stopped..",Toast.LENGTH_SHORT).show();
        level1 = level1/2;
        level2 = level2/2;
        level3 = level3/2;

        calories_burnt = calories_burnt + 0.175*(7.0*level1 + 12.0*level2 + 13.0*level3)*weight/60.0;//change weight later
        Toast.makeText(getApplicationContext(),String.valueOf(calories_burnt),Toast.LENGTH_LONG).show();
        String searchrecords=mySQLiteAdapter.SearchOneArgRecord(date_temp);

        if (searchrecords.isEmpty()) {
            mySQLiteAdapter.InsertRecord(date_temp,calories_burnt);
            MyToastMessage.myMessage(context, "value updated...");
        } else {

//            String arr = searchrecords.split("\n")[0];
//            int dt = Integer.valueOf(arr.split(" ")[0]);
//            double cal = Double.valueOf(arr.split(" ")[1]);
            int dt = getDate(searchrecords.split("\n")[0]);
            double cal = getCalories(searchrecords.split("\n")[0]);

            mySQLiteAdapter.UpdateVoidArgRecord(dt,cal+(calories_burnt));

            MyToastMessage.myMessage(context, "value entered");
        }
        finish();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        sensorManager.unregisterListener(this);
//        Toast.makeText(getApplicationContext(),"Job Ended..",Toast.LENGTH_SHORT).show();
//        level1 = level1/2;
//        level2 = level2/2;
//        level3 = level3/2;
//
//        double calories_burnt = 0.175*(7.0*level1 + 12.0*level2 + 13.0*level3)*50.0/60.0;//change weight later
//
//        String searchrecords=mySQLiteAdapter.SearchOneArgRecord(date_temp);
//
//        if (searchrecords.isEmpty()) {
//            mySQLiteAdapter.InsertRecord(date_temp,calories_burnt);
//            MyToastMessage.myMessage(context, "value updated...");
//        } else {
//
//            String arr = searchrecords.split("\n")[0];
//            int dt = Integer.valueOf(arr.split(" ")[0]);
//            double cal = Double.valueOf(arr.split(" ")[1]);
//
//            mySQLiteAdapter.UpdateVoidArgRecord(dt,cal+(calories_burnt));
//
//            MyToastMessage.myMessage(context, "value entered");
//        }
//    }

    public void check_history(View view) {
        Intent intent = new Intent(this,History_Data.class);
//        intent.putExtra("database",mySQLiteAdapter);

        Toast.makeText(getApplicationContext(),"Going to new page..",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
