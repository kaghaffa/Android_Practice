package com.example.thirtyinsixty.App_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by KayvonG on 8/5/13.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 3;
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


    interface TasksListener {
        void setTasks(ArrayList<String> tasks);
    }

    interface TaskListener {
        void returnFoundTask(int id);
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


    void findTaskAsync(TaskListener listener, String task) {
        AsyncTask<String, Void, Integer> asyncTask = new FindTask(listener);
        asyncTask.execute(task);
    }


    void saveTaskAsync(int position, String task) {
        AsyncTask<Void, Void, Void> asyncTask = new SaveTask(position, task);
        asyncTask.execute();
    }

    void getAllTasksAsync(TasksListener listener) {
        AsyncTask<Void, Void, ArrayList<String>> asyncTask = new GetAllTasks(listener);
        asyncTask.execute();
    }

    void deleteTaskAsync(Integer id) {
        AsyncTask<Integer, Void, Void> asyncTask = new DeleteTask();
        asyncTask.execute(id);
    }


    private class FindTask extends AsyncTask<String, Void, Integer> {

        private TaskListener listener = null;

        FindTask(TaskListener listener) {
            this.listener = listener;
        }

        @Override
        protected Integer doInBackground(String... params) {
            String[] args = { params[0].toString() };
            Cursor c = getReadableDatabase().rawQuery("SELECT _ID FROM Tasks WHERE task = ?", args);

            int result;
            if (c.getCount() > 0) {
                c.moveToFirst();
                result = c.getInt(0);
                c.close();
                return result;
            }

            c.close();
            return null;
        }


        @Override
        protected void onPostExecute(Integer id) {
            listener.returnFoundTask(id);
        }

    }


    private class SaveTask extends AsyncTask<Void, Void, Void> {
        private int id;
        private String task;

        SaveTask(int id, String task) {
            this.id = id;
            this.task = task;
        }


        @Override
        public Void doInBackground(Void... params) {

            if (id < 0) {
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_TASK, task);
                getWritableDatabase().insert(DatabaseHelper.TABLE_TASKS, null, values);
            } else {
                String[] args = { String.valueOf(id), task };
                getWritableDatabase().execSQL("REPLACE INTO Tasks (_ID, task) VALUES (?, ?)", args);
            }

            return null;
        }
    }


    private class GetAllTasks extends AsyncTask<Void, Void, ArrayList<String>> {

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
                c.moveToNext();
            }

            c.close();

            return tasks;
        }


        @Override
        public void onPostExecute(ArrayList<String> tasks) {
            listener.setTasks(tasks);
        }

    }


    private class DeleteTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            String[] args = { params[0].toString() };

            getWritableDatabase().delete(DatabaseHelper.TABLE_TASKS, DatabaseHelper.COLUMN_ID + "=" + params[0], null);

            return null;
        }



    }
}
