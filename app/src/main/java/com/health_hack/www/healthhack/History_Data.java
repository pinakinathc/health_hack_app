package com.health_hack.www.healthhack;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class History_Data extends AppCompatActivity {

    private MySQLiteAdapter mySQLiteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__data);
//        Intent i = getIntent();
//        mySQLiteAdapter = (MySQLiteAdapter) i.getSerializableExtra("database");
//        SQLiteDatabase db=mySQLiteAdapter.mySQLiteOpenHelper.getWritableDatabase();
        mySQLiteAdapter=new MySQLiteAdapter(this);
        SQLiteDatabase db=mySQLiteAdapter.mySQLiteOpenHelper.getWritableDatabase();
        Toast.makeText(getApplicationContext(),"History called..",Toast.LENGTH_SHORT).show();
        display();
    }

    public void display(){
        String temp = mySQLiteAdapter.DisplayAllRecord();
        if (!temp.isEmpty()) {
            Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
            String[] inp = temp.split("\n");
            LinearLayout lv = (LinearLayout) findViewById(R.id.history);

            for (String str : inp) {
                int dt = getDate(str);
                double cal = getCalories(str);
//
                LinearLayout lh = new LinearLayout(this);
                TextView tv1 = new TextView(this);
                tv1.setText(String.valueOf(dt) + "\t\t\t");
                lh.addView(tv1);
                //lv.addView(tv1);
                TextView tv2 = new TextView(this);
                tv2.setText(String.valueOf(cal));
                lh.addView(tv2);
                if (lv != null) {
                    lv.addView(lh);
                }
            }
        }
        else
            Toast.makeText(getApplicationContext(),"No data present..",Toast.LENGTH_LONG).show();
    }

    private int getDate(String str){
        return Integer.valueOf(str.split(" ")[0]);

    }

    private double getCalories(String str) {
        return Double.valueOf(str.split(" ")[1]);
    }

    public void go_back(View view) {
        Toast.makeText(getApplicationContext(),"Going back..",Toast.LENGTH_LONG).show();
        finish();
    }
}
