package com.example.thirtyinsixty;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thirtyinsixty.App_0.App_0;

public class MainActivity extends ListActivity {

    private static final String APP_0 = "FragmentMonster";


    private String[] apps = { APP_0 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = getListView();
        setListAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, apps));
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        String appName = apps[position];

        switch (position) {
            case 0:
                Intent in = new Intent(this, App_0.class);
                startActivity(in);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
