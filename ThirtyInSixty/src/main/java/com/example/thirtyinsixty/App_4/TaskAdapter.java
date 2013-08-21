package com.example.thirtyinsixty.App_4;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thirtyinsixty.R;

import java.util.List;

/**
 * Created by Kayvon on 8/19/13.
 */
public class TaskAdapter extends ArrayAdapter {

    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.task_row_item, tasks);
        this.context = context;
        this.tasks = tasks;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.task_row_item, null);
        }

        Task task = tasks.get(position);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.task_description);
        descriptionView.setText(task.getDescription());

        if (task.isCompleted()) {
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }
}
