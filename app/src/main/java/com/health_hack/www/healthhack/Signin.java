package com.health_hack.www.healthhack;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    MySQLiteAdapterSign mySQLiteAdapterSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mySQLiteAdapterSign=new MySQLiteAdapterSign(this);
        SQLiteDatabase db=mySQLiteAdapterSign.mySQLiteOpenHelperSign.getWritableDatabase();

    }

    public void signin(View view) {
        try {
            String user = ((EditText) findViewById(R.id.usersignin)).getText().toString();
            String pass = ((EditText) findViewById(R.id.passsignin)).getText().toString();

            String[] inp = mySQLiteAdapterSign.SearchVoidArgRecord(user, pass).split("\n")[0].split(" ");
            if (inp[1].equals(user) && inp[2].equals(pass)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Wrong Credentials..please try again", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Please enter your username and password..",Toast.LENGTH_LONG).show();
        }
    }

    public void signup(View view) {
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
}
