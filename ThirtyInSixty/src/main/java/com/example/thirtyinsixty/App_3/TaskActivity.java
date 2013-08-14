package com.example.thirtyinsixty.App_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.thirtyinsixty.R;

/**
 * Created by Kayvon on 8/5/13.
 */
public class TaskActivity extends FragmentActivity{

    public static final String EXTRA_POSITION = "position";
    private static final String SELECTED_TASK = "selectedTask";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            String task = getIntent().getStringExtra(SELECTED_TASK);
            Fragment f = TaskFragment.getInstance(task);
            getSupportFragmentManager().beginTransaction()
                                       .add(android.R.id.content, f).commit();

        }
    }

}
