package com.example.thirtyinsixty.App_3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

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
    private String[] allColumns = { COLUMN_ID, COLUMN_TASK };


    public static final String DATABASE_CREATE     = "CREATE TABLE " +
            TABLE_TASKS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_TASK + " text not null " + ");";


    interface TaskListener {
        void setTask(String task);
    }

    interface TasksListener {
        void setTasks(ArrayList<String> tasks);
    }

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


    void getTaskAsync(int position, TaskListener listener) {
        AsyncTask<Integer, Void, String> asyncTask = new GetTask(listener);
        asyncTask.execute(position);
    }


    void saveTaskAsync(int position, String task) {
        AsyncTask<Void, Void, Void> asyncTask = new SaveTask(position, task);
        asyncTask.execute();
    }

    void getAllTasksAsync(TasksListener listener) {
        AsyncTask<Void, Void, ArrayList<String>> asyncTask = new GetAllTasks(listener);
        asyncTask.execute();
    }

    private class GetTask extends AsyncTask<Integer, Void, String> {
        private TaskListener listener = null;

        GetTask(TaskListener listener) {
            this.listener = listener;
        }


        @Override
        protected String doInBackground(Integer... params) {
            String[] args = { params[0].toString() };
            Cursor c = getReadableDatabase().rawQuery("SELECT task FROM Tasks WHERE position = ?", args);

            c.moveToFirst();
            if (c.isAfterLast()) {
                return null;
            }

            String result = c.getString(0);
            c.close();

            return result;
        }


        @Override
        public void onPostExecute(String task) {
            listener.setTask(task);
        }
    }


    private class SaveTask extends AsyncTask<Void, Void, Void> {
        private int position;
        private String task;

        SaveTask(int position, String task) {
            this.position = position;
            this.task = task;
        }


        @Override
        public Void doInBackground(Void... params) {
            String[] args = { String.valueOf(position), task };

            getWritableDatabase().execSQL("INSERT OR REPLACE INTO Tasks (position, task) VALUES (?, ?)", args);

            return null;
        }
    }


    private class GetAllTasks extends AsyncTask<Void, Void, ArrayList<String>> {

        private ArrayList<String> tasks = new ArrayList<String>();
        private TasksListener listener = null;

        GetAllTasks(TasksListener listener) {
            this.listener = listener;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> tasks = new ArrayList<String>();
            Cursor c = getReadableDatabase().query(DatabaseHelper.TABLE_TASKS, allColumns, null, null, null, null, null);

            c.moveToFirst();
            while (!c.isAfterLast()) {
                tasks.add(c.getString(1));
            }

            c.close();

            return tasks;
        }


        @Override
        public void onPostExecute(ArrayList<String> tasks) {
            listener.setTasks(tasks);
        }

    }
}
