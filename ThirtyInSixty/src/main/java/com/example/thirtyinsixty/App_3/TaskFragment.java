package com.example.thirtyinsixty.App_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by KayvonG on 8/5/13.
 */
public class TaskFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private EditText taskInput;


    static TaskFragment getInstance(int position) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.tas_frag, container, false);
        int position = getArguments().getInt(KEY_POSITION);


        taskInput = result.findViewById(R.id.task_input);
        return result;
    }



}
