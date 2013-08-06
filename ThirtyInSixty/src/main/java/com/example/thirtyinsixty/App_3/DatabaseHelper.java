package com.example.thirtyinsixty.App_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KayvonG on 8/5/13.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper singleton = null;
    private Context context;


    public static final String TABLE_TASKS   = "Tasks";
    public static final String COLUMN_ID     = "_ID";
    public static final String COLUMN_TASK   = "task";


    public static final String DATABASE_CREATE     = "CREATE TABLE " +
            TABLE_TASKS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_TASK + " text not null " + ");";


    synchronized static DatabaseHelper getInstance(Context context) {
        if (singleton == null) {
            singleton = new DatabaseHelper(context.getApplicationContext());
        }

        return singleton;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(DATABASE_CREATE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " +
                        newVersion + ", which will destory old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }


}
