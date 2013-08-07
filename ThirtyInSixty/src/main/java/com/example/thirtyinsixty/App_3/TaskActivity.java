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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            int position = getIntent().getIntExtra(EXTRA_POSITION, -1);
            Fragment f = TaskFragment.getInstance(position);
            getSupportFragmentManager().beginTransaction()
                                       .add(android.R.id.content, f).commit();

        }
    }

}
