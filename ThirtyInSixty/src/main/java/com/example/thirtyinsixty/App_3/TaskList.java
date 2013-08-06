package com.example.thirtyinsixty.App_3;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TaskList implements ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }
    
}
