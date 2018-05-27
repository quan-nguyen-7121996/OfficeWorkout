package com.quancuteexample.officeworkout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;



public class LocalDatabase extends SQLiteOpenHelper{
    public LocalDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //write function
    public void QueryData(String sql){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public int GetCount(String tablename){
        int count=0;
        String sql="SELECT COUNT(*) FROM "+tablename;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            count=cursor.getInt(0);

        }
        cursor.close();
        return count;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
