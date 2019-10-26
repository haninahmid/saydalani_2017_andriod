package com.example.haninahmid.saydalani.rating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class starSqlhelper extends SQLiteOpenHelper {
    public static final  int DATABASE_VERSION=1;
    public static final  String DATABASE_NAME="star.db";

    private static final String  SQL_CREATE_STAR = String.format(" CREATE TABLE IF NOT EXISTS %s ( %s INTEGER, %s DOUBLE ) ", starbarclass.tablestar.TABLE_NAME, starbarclass.tablestar.COLUMN_ID, starbarclass.tablestar.COLUMN_STARS);

    private static final String SQL_DELETE_STARS= String.format("DROP TABLE IF EXISTS%s", starbarclass.tablestar.TABLE_NAME);


    public starSqlhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public  void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_STAR);
    }
    @Override
    public  void  onUpgrade(SQLiteDatabase db, int oldVersion ,int newVersion){
        db.execSQL(SQL_DELETE_STARS);
        onCreate(db);
    }
}
