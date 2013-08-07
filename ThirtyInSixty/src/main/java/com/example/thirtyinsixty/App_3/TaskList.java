package com.example.thirtyinsixty.App_3;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thirtyinsixty.R;

import java.util.ArrayList;

public class TaskList extends Activity implements DatabaseHelper.TasksListener {

    private ListView taskList;
    private ArrayAdapter arrayAdapter;
    private DatabaseHelper dbHelper;
    private ArrayList<String> tasks;
    private String[] testTasks = { "hello", "world" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        taskList = (ListView) findViewById(R.id.task_list);
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent in = new Intent(TaskList.this, TaskActivity.class);
                in.putExtra("selectedTask", tasks.get(position));
                startActivity(in);
            }
        });

        dbHelper = DatabaseHelper.getInstance(this);
        dbHelper.getAllTasksAsync(this);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_options, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_task) {
            Intent in = new Intent(this, TaskActivity.class);
            startActivity(in);
        }
        return true;
    }


    @Override
    public void setTasks(ArrayList<String> tasks) {
        if (tasks.size() > 0) {
            this.tasks = tasks;
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks);
            taskList.setAdapter(arrayAdapter);
        } else {
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, testTasks);
            taskList.setAdapter(arrayAdapter);
        }
    }
}
