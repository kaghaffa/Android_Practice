package com.example.thirtyinsixty.App_4;

import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thirtyinsixty.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ParseTaskList extends Activity implements AdapterView.OnItemClickListener {

    private EditText taskInput;
    private ListView taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_tasklist_main);

        Parse.initialize(this, "1LVcqzMZuCkwiXqb5pcBOtR6CuZ0EciLdtrGgtdf", "kic704lXlsSaZX8sK9vGwRHGpgaWW11dDqPA0IVT");
        ParseAnalytics.trackAppOpened(getIntent());

        ParseObject.registerSubclass(Task.class);

        taskInput = (EditText) findViewById(R.id.task_input);
        taskList = (ListView) findViewById(R.id.task_list);

        adapter = new TaskAdapter(this, new ArrayList<Task>());
        taskList.setAdapter(adapter);
        taskList.setOnItemClickListener(this);

        updateData();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Task task = (Task) adapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.task_description);

        task.setCompleted(!task.isCompleted());

        if (task.isCompleted()) {
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        task.saveEventually();
    }


    public void updateData() {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> tasks, ParseException e) {
                if (tasks != null) {
                    adapter.clear();
                    adapter.addAll(tasks);
                }
            }
        });
    }


    public void createTask(View v) {
        if (taskInput.getText().length() > 0) {
            Task t = new Task();
            t.setCompleted(false);
            t.setDescription(taskInput.getText().toString());
            t.saveEventually();
            taskInput.setText("");
            adapter.insert(t, 0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parse_task_list, menu);
        return true;
    }
    
}
