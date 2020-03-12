package com.example.txim.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="cpz_user1.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(id integer primary key autoincrement,name varchar(20)," +
                "password varchar(20),autograph varchar(225),user_name varchar(100)) ";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
