package com.example.thirtyinsixty.App_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thirtyinsixty.R;

/**
 * Created by Kayvon on 7/28/13.
 */
public class CrossfitTimerFragment extends Fragment implements OnClickListener {

    private static final String KEY_POSITION = "position";
    private Button startStopButton;
    private Button resetButton;
    private TextView stopwatch;

    static CrossfitTimerFragment newInstance(int position) {
        CrossfitTimerFragment fragment = new CrossfitTimerFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.crossfit_timer_fragment, container, false);
        int position = getArguments().getInt(KEY_POSITION, -1);

        stopwatch = (TextView) result.findViewById(R.id.timer);
        startStopButton = (Button) result.findViewById(R.id.start_stop_button);
        resetButton = (Button) result.findViewById(R.id.reset_button);
        startStopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        return result;
    }

    static String getTitle(Context context, int position) {
        if (position == 0) {
            return context.getString(R.string.stopwatch_title);
        } else if (position == 1) {
            return context.getString(R.string.countdown_title);
        } else {
            return context.getString(R.string.tabata_title);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_stop_button:
                if (timerStarted) {
                    timerStarted = false;
                    startStopButton.setText("Start");
                } else {
                    timerStarted = true;
                    startStopButton.setText("Stop");
                }
                break;
            case R.id.reset_button:
                break;
        }
    }
}
