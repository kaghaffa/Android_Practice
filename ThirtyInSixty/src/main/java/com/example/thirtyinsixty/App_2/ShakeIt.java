package com.example.thirtyinsixty.App_2;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.example.thirtyinsixty.R;

public class ShakeIt extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    TextView score;
    TextView time;

    private float xAccel;
    private float yAccel;
    private float zAccel;


    private float xPrevAccel;
    private float yPrevAccel;
    private float zPrevAccel;

    private boolean firstUpdate = true;
    private boolean shakeInitiated = false;
    private final float shakeThreshold = 5.0f;

    private static int points = 0;
    private static boolean gameEnded = false;
    private int timeLeft = 10000;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_it_main);

        score = (TextView) findViewById(R.id.shake_it_score);
        time = (TextView) findViewById(R.id.timer);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (savedInstanceState != null) {
            timeLeft = savedInstanceState.getInt("TimeLeft");
        }

        if (timeLeft > 0) {

            timer = new CountDownTimer(timeLeft, 1000) {
                public void onTick(long millisUntilFinished) {
                    int secondsLeft = (int) (millisUntilFinished / 1000);
                    time.setText("Time left: " + secondsLeft);
                    timeLeft = (int) millisUntilFinished;
                }

                public void onFinish() {
                    timeLeft = 0;
                    time.setText("Time's up!");
                    gameEnded = true;
                }
            }.start();
        } else {
            time.setText("Times up!");
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!gameEnded && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            updateAccelParameters(event.values[0], event.values[1], event.values[2]);

            if ( !shakeInitiated && isAccelerationChanged() ) {
                shakeInitiated = true;
            } else if ( shakeInitiated && isAccelerationChanged() ) {
                executeAccelerometerAction();
            } else if ( shakeInitiated && !isAccelerationChanged() ) {
                shakeInitiated = false;
            }
        }
    }


    private void updateAccelParameters(float xNew, float yNew, float zNew) {
        if (firstUpdate) {
            xPrevAccel = xNew;
            yPrevAccel = yNew;
            zPrevAccel = zNew;
            firstUpdate = false;
        } else {
            xPrevAccel = xAccel;
            yPrevAccel = yAccel;
            zPrevAccel = zAccel;
        }

        xAccel = xNew;
        yAccel = yNew;
        zAccel = zNew;
    }


    private boolean isAccelerationChanged() {
        float deltaX = Math.abs(xAccel - xPrevAccel);
        float deltaY = Math.abs(yAccel - yPrevAccel);
        float deltaZ = Math.abs(zAccel - zPrevAccel);

        return     (deltaX > shakeThreshold && deltaY > shakeThreshold)
                || (deltaX > shakeThreshold && deltaZ > shakeThreshold)
                || (deltaZ > shakeThreshold && deltaY > shakeThreshold);
    }


    private void executeAccelerometerAction() {
        points++;
        score.setText("Points " + points);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shake_it, menu);
        return true;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        score.setText("Points " + points);
        time.setText("Time left: " + timeLeft / 1000);
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        timer.cancel();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("TimeLeft", timeLeft);
    }
    
}
