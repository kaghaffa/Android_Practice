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
 * Created by Kayvon on 7/30/13.
 */
public class EmomFragment extends Fragment {

    private static final String KEY_POSITION = "position";

    TextView emomTitle;
    TextView timer;
    Button numpad0, numpad1, numpad2, numpad3, numpad4, numpad5, numpad6, numpad7, numpad8, numpad9;
    Button backspace;
    Button start;

    static EmomFragment newInstance(int position) {
        EmomFragment fragment = new EmomFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.crossfit_emom_fragment, container, false);
        initUI(result);

        return result;
    }

    static String getTitle(Context context, int position) {
        return "EMOM";
    }


    public void onNumpadClick(View view) {
        int num = view;
    }

    public void onBackspaceClick(View view)


    public void onStartClick(View view) {

    }


    private void initUI(View layout) {
        emomTitle = (TextView) layout.findViewById(R.id.emom_title);
        timer = (TextView) layout.findViewById(R.id.timer);
        numpad0 = (Button) layout.findViewById(R.id.numpad_0);
        numpad1 = (Button) layout.findViewById(R.id.numpad_1);
        numpad2 = (Button) layout.findViewById(R.id.numpad_2);
        numpad3 = (Button) layout.findViewById(R.id.numpad_3);
        numpad4 = (Button) layout.findViewById(R.id.numpad_4);
        numpad5 = (Button) layout.findViewById(R.id.numpad_5);
        numpad6 = (Button) layout.findViewById(R.id.numpad_6);
        numpad7 = (Button) layout.findViewById(R.id.numpad_7);
        numpad8 = (Button) layout.findViewById(R.id.numpad_8);
        numpad9 = (Button) layout.findViewById(R.id.numpad_9);
        backspace = (Button) layout.findViewById(R.id.numpad_backspace);
        start = (Button) layout.findViewById(R.id.start_stop_button);
    }

}
