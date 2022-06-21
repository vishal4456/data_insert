package com.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper
{
    private final static String DATABASE_NAME = "Student_db";
    private final static int DATABASE_VERSION=2;
    private final Context context;
    private final static String TABLE_NAME="Student";
    private final static String RNO = "rno";
    private final static String NAME = "name";
    private final static String PER = "per";
    String TAG= "Student_database";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Log.d(TAG, "constructor is called");
        Toast.makeText(context,"constructor is called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table "+TABLE_NAME+"("+RNO+"integer,"+NAME+"text,"+PER+"float)";
        Log.d(TAG,"OnCreate method is called");
        Toast.makeText(context,"onCerate method is called",Toast.LENGTH_LONG).show();
        Log.d(TAG,CREATE_TABLE);
        try {
            db.execSQL(CREATE_TABLE);
            Log.d(TAG,"Table is created");
            Toast.makeText(context,"Table is created",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_LONG).show();
            Log.d(TAG,"Exception"+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context,"onUpgrade method is called",Toast.LENGTH_LONG).show();
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
        Log.d(TAG,"");

    }

    public boolean insertData(Integer rno, String name, Float per) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RNO,rno);
        cv.put(NAME,name);
        cv.put(PER,per);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
