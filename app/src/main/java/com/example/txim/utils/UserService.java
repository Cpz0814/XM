package com.example.txim.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
    private DatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where name=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    public boolean register(UserBean user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(name,password,autograph,user_name) values(?,?,?,?)";
        Object obj[]={user.getName(),user.getPassword(),user.getAutograph()};
        sdb.execSQL(sql, obj);
        return true;
    }
    public UserBean find(String name){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from user where name=?", new String[]{name});
        if (cursor.moveToFirst()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String uname=cursor.getString(cursor.getColumnIndex("name"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            String autograph=cursor.getString(cursor.getColumnIndex("autograph"));
            String user_name=cursor.getString(cursor.getColumnIndex("user_name"));
            UserBean bean=new UserBean();
            bean.setId(id);
            bean.setName(uname);
            bean.setPassword(password);
            bean.setAutograph(autograph);
            bean.setUsername(user_name);
            return bean;
        }
        cursor.close();
        return null;
    }
    public UserBean find2(String autograph){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from user where autograph=?", new String[]{autograph});
        if (cursor.moveToFirst()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String uname=cursor.getString(cursor.getColumnIndex("name"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            String autographs=cursor.getString(cursor.getColumnIndex("autograph"));
            String user_name=cursor.getString(cursor.getColumnIndex("user_name"));
            UserBean bean=new UserBean();
            bean.setId(id);
            bean.setName(uname);
            bean.setPassword(password);
            bean.setAutograph(autographs);
            bean.setUsername(user_name);
            return bean;
        }
        cursor.close();
        return null;
    }
}
