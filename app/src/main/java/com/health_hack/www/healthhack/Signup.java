package com.health_hack.www.healthhack;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    String sex;
    EditText username10,password10,age10,height10,weight10,phone10,email10,confirmpassword10;
    RadioGroup sex10;
    MySQLiteAdapterSign mySQLiteAdapterSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Context context=this;

        mySQLiteAdapterSign=new MySQLiteAdapterSign(context);
        SQLiteDatabase db=mySQLiteAdapterSign.mySQLiteOpenHelperSign.getWritableDatabase();

    }

    public void signup_submit(View view) {
        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);
        username10 = (EditText) findViewById(R.id.username);
        password10 = (EditText) findViewById(R.id.password);
        confirmpassword10 = (EditText) findViewById(R.id.newpass);
        email10 = (EditText) findViewById(R.id.email);
        phone10 = (EditText) findViewById(R.id.phone);
        age10 = (EditText) findViewById(R.id.age);
        height10 = (EditText) findViewById(R.id.height);
        weight10 = (EditText) findViewById(R.id.weight);
        sex10 = (RadioGroup) findViewById(R.id.sex);

        String pass = password10.getText().toString();
        String user = username10.getText().toString();
        String a = age10.getText().toString();
        String h = height10.getText().toString();
        String w = weight10.getText().toString();
        String ph = phone10.getText().toString();
        String mail = email10.getText().toString();
        String conpass = confirmpassword10.getText().toString();

        if (pass != null && user != null && mail != null && sex != null && ph != null && a != null && h != null && w != null) {
            if (pass.equals(conpass)) {
                mySQLiteAdapterSign.InsertRecord(user, pass, a, sex, h, w, ph, mail);
                Intent second = new Intent(this, MainActivity.class);
                startActivity(second);
            } else
                Toast.makeText(getApplicationContext(), "Password does not match Confirm password", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "INCOMPLETE INFORMATION", Toast.LENGTH_SHORT).show();


        finish();
    }

    public void rdbtnclick(View view) {
        boolean status = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.male:
                if (status) sex = "M";
                else sex = "F";
                break;
            case R.id.female:
                if (status) sex = "F";
                else sex = "M";
                break;
        }

    }
}
