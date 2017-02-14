package com.health_hack.www.healthhack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HOME on 03-Feb-2017.
 */

public class MySQLiteAdapterSign {
    Context context;
    MySQLiteOpenHelperSign mySQLiteOpenHelperSign;
    public static double Weight;
    public MySQLiteAdapterSign(Context context){
        this.context=context;
        mySQLiteOpenHelperSign=new MySQLiteOpenHelperSign(context);
    }

    public long InsertRecord(String name, String password, String age, String sex, String height, String weight, String phone, String email) {
        //insert into emp_table(Emp_Name,Emp_Password) values('amit','amit123');
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(mySQLiteOpenHelperSign.NAME, name);
        contentValues.put(mySQLiteOpenHelperSign.PASSWORD, password);
        contentValues.put(mySQLiteOpenHelperSign.AGE, age);
        contentValues.put(mySQLiteOpenHelperSign.SEX, sex);
        contentValues.put(mySQLiteOpenHelperSign.HEIGHT, height);
        contentValues.put(mySQLiteOpenHelperSign.WEIGHT, weight);
        contentValues.put(mySQLiteOpenHelperSign.PHONE, phone);
        contentValues.put(mySQLiteOpenHelperSign.EMAIL, email);
        long id=db.insert(mySQLiteOpenHelperSign.TABLE_NAME, null, contentValues);
        //Weight=Double.valueOf(weight);
        return id;
    }

    public String DisplayAllRecord() {
        //select _id,Emp_Name,Emp_Password from emp_table;
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        String[] columns={MySQLiteOpenHelperSign.UID,MySQLiteOpenHelperSign.NAME,MySQLiteOpenHelperSign.PASSWORD,
                MySQLiteOpenHelperSign.AGE,MySQLiteOpenHelperSign.SEX,MySQLiteOpenHelperSign.HEIGHT,MySQLiteOpenHelperSign.WEIGHT,
                MySQLiteOpenHelperSign.PHONE, MySQLiteOpenHelperSign.EMAIL};
        Cursor cursor=db.query(MySQLiteOpenHelperSign.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelperSign.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelperSign.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelperSign.PASSWORD);
            int index4=cursor.getColumnIndex(MySQLiteOpenHelperSign.AGE);
            int index5=cursor.getColumnIndex(MySQLiteOpenHelperSign.SEX);
            int index6=cursor.getColumnIndex(MySQLiteOpenHelperSign.HEIGHT);
            int index7=cursor.getColumnIndex(MySQLiteOpenHelperSign.WEIGHT);
            int index8=cursor.getColumnIndex(MySQLiteOpenHelperSign.PHONE);
            int index9=cursor.getColumnIndex(MySQLiteOpenHelperSign.EMAIL);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            String age=cursor.getString(index4);
            String sex=cursor.getString(index5);
            String height=cursor.getString(index6);
            String weight=cursor.getString(index7);
            String phone=cursor.getString(index8);
            String email=cursor.getString(index9);
            buffer.append(cid+" "+name+" "+password+" "+age+" "+sex+" "+height+" "+weight+" "+phone+" "+email+"\n");
        }
        return buffer.toString();
    }

    public String SearchOneArgRecord(String usrname) {
        //select _id,Emp_Name,Emp_Password from emp_table where Emp_Name='amit' and Emp_Password='abc123';
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        String[] columns={MySQLiteOpenHelperSign.UID,MySQLiteOpenHelperSign.NAME,MySQLiteOpenHelperSign.PASSWORD,
                MySQLiteOpenHelperSign.AGE,MySQLiteOpenHelperSign.SEX,MySQLiteOpenHelperSign.HEIGHT,MySQLiteOpenHelperSign.WEIGHT,
                MySQLiteOpenHelperSign.PHONE, MySQLiteOpenHelperSign.EMAIL};
        Cursor cursor=db.query(MySQLiteOpenHelperSign.TABLE_NAME, columns, MySQLiteOpenHelperSign.NAME+" = '"+usrname+"'", null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelperSign.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelperSign.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelperSign.PASSWORD);
            int index4=cursor.getColumnIndex(MySQLiteOpenHelperSign.AGE);
            int index5=cursor.getColumnIndex(MySQLiteOpenHelperSign.SEX);
            int index6=cursor.getColumnIndex(MySQLiteOpenHelperSign.HEIGHT);
            int index7=cursor.getColumnIndex(MySQLiteOpenHelperSign.WEIGHT);
            int index8=cursor.getColumnIndex(MySQLiteOpenHelperSign.PHONE);
            int index9=cursor.getColumnIndex(MySQLiteOpenHelperSign.EMAIL);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            String age=cursor.getString(index4);
            String sex=cursor.getString(index5);
            String height=cursor.getString(index6);
            String weight=cursor.getString(index7);
            String phone=cursor.getString(index8);
            String email=cursor.getString(index9);
            buffer.append(cid+" "+name+" "+password+" "+age+" "+sex+" "+height+" "+weight+" "+phone+" "+email+"\n");
        }
        return buffer.toString();
    }

    public int DeleteOneArgRecord(String usrname) {
        //delete from emp_table where Emp_Name='amit'
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        int count=db.delete(MySQLiteOpenHelperSign.TABLE_NAME, MySQLiteOpenHelperSign.NAME+" = '"+usrname+"'", null);
        return count;
    }

    public int DeleteVoidArgRecord(String usrname) {
        ////delete from emp_table where Emp_Name=?
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        String[] arguments={usrname};
        int count=db.delete(MySQLiteOpenHelperSign.TABLE_NAME, MySQLiteOpenHelperSign.NAME+" = ? ", arguments);

        return count;
    }

    public String SearchVoidArgRecord(String usrname,String usrpassword) {
        //select _id,Emp_Name,Emp_Password from emp_table where Emp_Name='amit';
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        String[] columns={MySQLiteOpenHelperSign.UID,MySQLiteOpenHelperSign.NAME,MySQLiteOpenHelperSign.PASSWORD,
        MySQLiteOpenHelperSign.AGE,MySQLiteOpenHelperSign.SEX,MySQLiteOpenHelperSign.HEIGHT,MySQLiteOpenHelperSign.WEIGHT,
        MySQLiteOpenHelperSign.PHONE, MySQLiteOpenHelperSign.EMAIL};
        String[] selectionArgs={usrname,usrpassword};
        Cursor cursor=db.query(MySQLiteOpenHelperSign.TABLE_NAME, columns, MySQLiteOpenHelperSign.NAME+" = ? and "+MySQLiteOpenHelperSign.PASSWORD + "= ?"
                , selectionArgs, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelperSign.UID);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelperSign.NAME);
            int index3=cursor.getColumnIndex(MySQLiteOpenHelperSign.PASSWORD);
            int index4=cursor.getColumnIndex(MySQLiteOpenHelperSign.AGE);
            int index5=cursor.getColumnIndex(MySQLiteOpenHelperSign.SEX);
            int index6=cursor.getColumnIndex(MySQLiteOpenHelperSign.HEIGHT);
            int index7=cursor.getColumnIndex(MySQLiteOpenHelperSign.WEIGHT);
            int index8=cursor.getColumnIndex(MySQLiteOpenHelperSign.PHONE);
            int index9=cursor.getColumnIndex(MySQLiteOpenHelperSign.EMAIL);
            int cid=cursor.getInt(index1);
            String name=cursor.getString(index2);
            String password=cursor.getString(index3);
            String age=cursor.getString(index4);
            String sex=cursor.getString(index5);
            String height=cursor.getString(index6);
            String weight=cursor.getString(index7);
            String phone=cursor.getString(index8);
            String email=cursor.getString(index9);
            buffer.append(cid+" "+name+" "+password+" "+age+" "+sex+" "+height+" "+weight+" "+phone+" "+email+"\n");
        }
        return buffer.toString();
    }

    public int UpdateOneArgRecord(String usrname,String newpassword) {
        //update emp_table set Emp_Password='amit123456' where Emp_Name='amit'
        //MyToastMessage.myMessage(context, "Executing UpdateOneArgRecord");
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelperSign.PASSWORD, newpassword);
        int count=db.update(MySQLiteOpenHelperSign.TABLE_NAME, contentValues, MySQLiteOpenHelperSign.NAME+" = '"+usrname+"'" , null);

        return count;
    }

    public int UpdateVoidArgRecord(String usrname,String newpassword) {
        //update emp_table set Emp_Password=? where Emp_Name=?
        //MyToastMessage.myMessage(context, "Executing UpdateVoidArgRecord");
        SQLiteDatabase db=mySQLiteOpenHelperSign.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelperSign.PASSWORD, newpassword);
        String[] arguments={usrname};
        int count=db.update(MySQLiteOpenHelperSign.TABLE_NAME, contentValues, MySQLiteOpenHelperSign.NAME+" = ? ", arguments);

        return count;
    }




    public class MySQLiteOpenHelperSign extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "employee.db";
        private static final String TABLE_NAME = "emp_table";
        private static final int DATABASE_VERSION = 2;
        private static final String UID = "_id";
        private static final String NAME = "Emp_Name";
        private static final String PASSWORD = "Emp_Password";
        private static final String AGE = "Age";
        private static final String SEX = "Sex";
        private static final String HEIGHT = "Height";
        private static final String WEIGHT = "Weight";
        private static final String PHONE = "Phone";
        private static final String EMAIL = "Email";
        //		private static final String AGE="Emp_Age";
        //		private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255),"+AGE+" INTEGER);";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME +
                " VARCHAR(255)," + PASSWORD + " VARCHAR(255),"+AGE +" VARCHAR(255),"+SEX+" VARCHAR(255),"+
                HEIGHT+" VARCHAR(255),"+WEIGHT+" VARCHAR(255),"+PHONE+" VARCHAR(255),"+EMAIL+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        private Context context;

        public MySQLiteOpenHelperSign(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            //MyToastMessage.myMessage(context, "Constructor called...");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //MyToastMessage.myMessage(context, "onCreate called...");
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //e.printStackTrace();
                //MyToastMessage.myMessage(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newdVersion) {
            try {
                //MyToastMessage.myMessage(context, "onUpgrade called...");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                //e.printStackTrace();
                //MyToastMessage.myMessage(context, "" + e);
                Log.d("TEST", "" + e);
            }
        }
    }
}
