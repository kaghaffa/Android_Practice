package com.example.thirtyinsixty.App_1;

/**
 * Created by Kayvon on 7/30/13.
 */
public class StopWatch {

    private long startTime;
    private long endTime;
    private long pauseOffset
    private boolean isRunning;


    public StopWatch() {
        reset();
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            endTime = System.currentTimeMillis();
            pauseOffset = getElapsedTime();
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        pauseOffset = 0;
        isRunning = false;
    }


    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return System.currentTimeMillis() - startTime - pauseOffset;
        }
    }


    public String getTimeAsString() {
        long time = getElapsedTime();
        long
    }


    public long getStartTime() {
        return startTime;
    }


    public long getEndTime() {
        return endTime;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
