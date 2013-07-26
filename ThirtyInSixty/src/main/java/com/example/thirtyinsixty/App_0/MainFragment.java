package com.example.thirtyinsixty.App_0;


import android.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



/**
 * Created by Kayvon on 7/26/13.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(, container, false);
        result.findViewById(R.id.).setOnClickListener(this);


        return(result);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "BUTTON CLICKED!", Toast.LENGTH_LONG).show();
    }
}
