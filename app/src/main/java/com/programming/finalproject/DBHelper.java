package com.programming.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "MyPlate.db";

    public DBHelper(Context context) {
        super(context, "MyPlate.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (username TEXT primary key, password TEXT, name TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users" );

    }

    public boolean insertData(String username, String password, String name){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        //contentValues.put("DOB", dob);

        long results = MyDB.insert("users", null, contentValues);

        if(results ==-1) return false;
        else
            return true;
    }

    public boolean usernameExists(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String getName(String username){
       SQLiteDatabase MyDB = this.getReadableDatabase();
       Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});
       String name = "";

       if(cursor.moveToFirst()) {
           int columnIndex = cursor.getColumnIndex("name");
           if(columnIndex >= 0){
               name = cursor.getString(columnIndex);
           }
       }
       cursor.close();
       MyDB.close();
       return name;

    }
}
