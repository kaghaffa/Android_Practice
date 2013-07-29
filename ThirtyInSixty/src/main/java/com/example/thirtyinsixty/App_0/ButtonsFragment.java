package com.example.thirtyinsixty.App_0;


import com.example.thirtyinsixty.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by Kayvon on 7/26/13.
 */
public class ButtonsFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.buttons_frag, container, false);
        result.findViewById(R.id.button_0).setOnClickListener(this);

        return(result);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "BUTTON CLICKED!", Toast.LENGTH_LONG).show();
    }
}
