package com.example.thirtyinsixty.App_0;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thirtyinsixty.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kayvon on 7/25/13.
 */
public class App_0 extends Activity  implements View.OnClickListener {
    TextView time, score;
    ArrayList<Button> buttons = new ArrayList<Button>();
    Button b0, b1, b2, b3, b4, b5, b6;
    Random random;

    private static int numPoints = 0;
    private static int secondsLeft = 0;
    private static int targetButtonNumber;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_0);

        time = (TextView) findViewById(R.id.time);
        score = (TextView) findViewById(R.id.score);

        b0 = (Button) findViewById(R.id.button_0);
        b1 = (Button) findViewById(R.id.button_1);
        b2 = (Button) findViewById(R.id.button_2);
        b3 = (Button) findViewById(R.id.button_3);
        b4 = (Button) findViewById(R.id.button_4);
        b5 = (Button) findViewById(R.id.button_5);
        b6 = (Button) findViewById(R.id.button_6);
        buttons.add(b0);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        random = new Random();
        targetButtonNumber = random.nextInt(7);
        buttons.get(targetButtonNumber).setTextColor(Color.RED);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                buttons.get(targetButtonNumber).setTextColor(Color.BLACK);
                secondsLeft = (int) (millisUntilFinished / 1000);
                targetButtonNumber = random.nextInt(7);
                buttons.get(targetButtonNumber).setTextColor(Color.RED);
                time.setText("Time left: " + secondsLeft);
            }

            public void onFinish() {
                secondsLeft = 0;
                time.setText("Time's up!");
                for (Button button : buttons) {
                    button.setEnabled(false);
                    button.setTextColor(Color.LTGRAY);
                }
            }
        }.start();

    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) findViewById(view.getId());
        int buttonNumber = Integer.parseInt(clickedButton.getText().toString());
        if (buttonNumber == targetButtonNumber) {
            numPoints++;
            score.setText("Score: " + numPoints);
        }
    }

}