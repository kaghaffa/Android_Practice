package com.example.thirtyinsixty.App_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thirtyinsixty.R;

/**
 * Created by KayvonG on 8/5/13.
 */
public class TaskFragment extends Fragment implements DatabaseHelper.TaskListener {

    private static final String SELECTED_TASK = "selectedTask";
    private static final String KEY_POSITION = "position";
    private EditText taskInput;
    private Button saveTaskButton;

    private boolean isDeleted = false;
    private int taskId = -1;


    static TaskFragment getInstance(String task) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_TASK, task);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.task_frag, container, false);

        taskInput = (EditText) result.findViewById(R.id.task_input);
        saveTaskButton = (Button) result.findViewById(R.id.save_task_button);
        saveTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), TaskList.class);
                startActivity(in);
            }
        });

        String task = getArguments().getString(SELECTED_TASK);

        if (task != null) {
            taskInput.setText(task);
            DatabaseHelper.getInstance(getActivity()).findTaskAsync(this, task);
        }

        setHasOptionsMenu(true);

        return result;
    }


    @Override
    public void onPause() {
        if (!isDeleted) {
            DatabaseHelper.getInstance(getActivity()).saveTaskAsync(taskId, taskInput.getText().toString());
        }
        super.onPause();
    }


    @Override
    public void returnFoundTask(int id) {
        this.taskId = id;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            isDeleted = true;
            DatabaseHelper.getInstance(getActivity()).deleteTaskAsync(this.taskId);
            Toast.makeText(getActivity(), "Task deleted", Toast.LENGTH_LONG);
            Intent in = new Intent(getActivity(), TaskList.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }

}
