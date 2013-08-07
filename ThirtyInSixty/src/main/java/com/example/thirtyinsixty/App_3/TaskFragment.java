package com.example.thirtyinsixty.App_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.thirtyinsixty.R;

/**
 * Created by KayvonG on 8/5/13.
 */
public class TaskFragment extends Fragment implements DatabaseHelper.TaskListener {

    private static final String KEY_POSITION = "position";
    private EditText taskInput;
    private Button saveTaskButton;


    static TaskFragment getInstance(int position) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.task_frag, container, false);
        int position = getArguments().getInt(KEY_POSITION);

        if (position >= 0) {
            DatabaseHelper.getInstance(getActivity()).getTaskAsync(position, this);
        }


        taskInput = (EditText) result.findViewById(R.id.task_input);
        saveTaskButton = (Button) result.findViewById(R.id.save_task_button);
        saveTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), TaskActivity.class);
                startActivity(in);
            }
        });
        return result;
    }

    @Override
    public void setTask(String task) {
        taskInput.setText(task);
    }


    @Override
    public void onPause() {
        int position = getArguments().getInt(KEY_POSITION, -1);

        DatabaseHelper.getInstance(getActivity()).saveTaskAsync(position, taskInput.getText().toString());

        super.onPause();
    }

}
