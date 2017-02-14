package com.health_hack.www.healthhack;
/**
 * Created by HP on 10-Feb-17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by HOME on 03-Feb-2017.
 */
public class MySQLiteAdapter {
    Context context;
    MySQLiteOpenHelper mySQLiteOpenHelper;

    public MySQLiteAdapter(Context context){
        this.context=context;
        mySQLiteOpenHelper=new MySQLiteOpenHelper(context);
    }

    public long InsertRecord(int date, double calories) {
        //insert into emp_table(Emp_Name,Emp_Password) values('amit','amit123');
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelper.DATE, date);
        contentValues.put(MySQLiteOpenHelper.CALORIES, calories);
        long id=db.insert(MySQLiteOpenHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String DisplayAllRecord() {
        //select _id,Emp_Name,Emp_Password from emp_table;
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpenHelper.DATE,MySQLiteOpenHelper.CALORIES};
        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.DATE);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.CALORIES);
            int date=cursor.getInt(index1);
            double calories=cursor.getDouble(index2);
            buffer.append(String.valueOf(date)+" "+String.valueOf(calories)+"\n");
        }
        return buffer.toString();
    }

    public String SearchOneArgRecord(int date) {
        //select _id,Emp_Name,Emp_Password from emp_table where Emp_Name='amit' and Emp_Password='abc123';
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] columns={MySQLiteOpenHelper.DATE,MySQLiteOpenHelper.CALORIES};
        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteOpenHelper.DATE+" = '"+date+"'", null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()) {
            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.DATE);
            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.CALORIES);
            int dt=cursor.getInt(index1);
            double cal=cursor.getDouble(index2);
            buffer.append(String.valueOf(dt)+" "+String.valueOf(cal)+"\n");
        }
        return buffer.toString();
    }

//    public int DeleteOneArgRecord(String usrname) {
//        //delete from emp_table where Emp_Name='amit'
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        int count=db.delete(MySQLiteOpenHelper.TABLE_NAME, MySQLiteOpenHelper.NAME+" = '"+usrname+"'", null);
//        return count;
//    }

    public int DeleteVoidArgRecord(int date) {
        ////delete from emp_table where Emp_Name=?
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        String[] arguments={String.valueOf(date)};
        int count=db.delete(MySQLiteOpenHelper.TABLE_NAME, MySQLiteOpenHelper.DATE+" = ? ", arguments);

        return count;
    }

//    public String SearchVoidArgRecord(String usrname,String usrpassword) {
//        //select _id,Emp_Name,Emp_Password from emp_table where Emp_Name='amit';
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        String[] columns={MySQLiteOpenHelper.UID,MySQLiteOpenHelper.NAME,MySQLiteOpenHelper.PASSWORD};
//        String[] selectionArgs={usrname,usrpassword};
//        Cursor cursor=db.query(MySQLiteOpenHelper.TABLE_NAME, columns, MySQLiteOpenHelper.NAME+" = ? and "+MySQLiteOpenHelper.PASSWORD + "= ?"
//                , selectionArgs, null, null, null);
//        StringBuffer buffer=new StringBuffer();
//        while(cursor.moveToNext()) {
//            int index1=cursor.getColumnIndex(MySQLiteOpenHelper.UID);
//            int index2=cursor.getColumnIndex(MySQLiteOpenHelper.NAME);
//            int index3=cursor.getColumnIndex(MySQLiteOpenHelper.PASSWORD);
//            int cid=cursor.getInt(index1);
//            String name=cursor.getString(index2);
//            String password=cursor.getString(index3);
//            buffer.append(cid+" "+name+" "+password+"\n");
//        }
//        return buffer.toString();
//    }

//    public int UpdateOneArgRecord(String usrname,String newpassword) {
//        //update emp_table set Emp_Password='amit123456' where Emp_Name='amit'
//        MyToastMessage.myMessage(context, "Executing UpdateOneArgRecord");
//        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(MySQLiteOpenHelper.PASSWORD, newpassword);
//        int count=db.update(MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteOpenHelper.NAME+" = '"+usrname+"'" , null);
//
//        return count;
//    }
//
    public int UpdateVoidArgRecord(int date,double calories) {
        //update emp_table set Emp_Password=? where Emp_Name=?
        MyToastMessage.myMessage(context, "Executing UpdateVoidArgRecord");
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MySQLiteOpenHelper.CALORIES, calories);
        contentValues.put(MySQLiteOpenHelper.DATE,date);
        String[] arguments={String.valueOf(date)};
        int count=db.update(MySQLiteOpenHelper.TABLE_NAME, contentValues, MySQLiteOpenHelper.DATE+" = ? ", arguments);

        return count;
    }




    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "health.db";
        private static final String TABLE_NAME = "daily_register";
        private static final int DATABASE_VERSION = 2;
        //private static final String UID = "_id";
        //private static final String NAME = "Emp_Name";
        //private static final String PASSWORD = "Emp_Password";

        private static final String DATE = "_date";
        private static final String CALORIES = "_calories";

        //		private static final String AGE="Emp_Age";
        //		private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255),"+AGE+" INTEGER);";
        //private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + DATE + " INTEGER PRIMARY KEY AUTOINCREMENT," + CALORIES +
        //        " VARCHAR(255)," + PASSWORD + " VARCHAR(255));";

        private static final  String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+DATE+" INTEGER PRIMARY KEY,"+CALORIES+" DOUBLE);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        private Context context;

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            MyToastMessage.myMessage(context, "Constructor called...");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                MyToastMessage.myMessage(context, "onCreate called...");
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //e.printStackTrace();
                MyToastMessage.myMessage(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newdVersion) {
            try {
                MyToastMessage.myMessage(context, "onUpgrade called...");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                //e.printStackTrace();
                MyToastMessage.myMessage(context, "" + e);
                Log.d("TEST", "" + e);
            }
        }
    }
}

