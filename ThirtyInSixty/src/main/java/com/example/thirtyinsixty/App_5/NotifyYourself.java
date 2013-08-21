package com.example.thirtyinsixty.App_5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;

import com.example.thirtyinsixty.R;

public class NotifyYourself extends Activity {

    private static final int NOTIFY_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder normal = buildNormal();
        NotificationCompat.InboxStyle big = new NotificationCompat.InboxStyle(normal);

        mgr.notify(NOTIFY_ID,
                big.build());
        finish();

    }


    private NotificationCompat.Builder buildNormal() {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setAutoCancel(true)
         .setDefaults(Notification.DEFAULT_ALL)
         .setWhen(System.currentTimeMillis())
         .setContentTitle(getString(R.string.notify_yourself))
         .setContentText(getString(R.string.notify_yourself_text))
         .setContentIntent(buildPendingIntent(Settings.ACTION_SECURITY_SETTINGS))
         .setTicker(getString(R.string.notify_yourself))
         .setPriority(Notification.PRIORITY_HIGH)
         .addAction(android.R.drawable.ic_media_play,
                 getString(R.string.start),
                 buildPendingIntent(Settings.ACTION_SETTINGS))
         .setSmallIcon(android.R.drawable.ic_media_play);

        return b;
    }


    private PendingIntent buildPendingIntent(String action) {
        Intent i = new Intent(action);
        return (PendingIntent.getActivity(this, 0, i, 0));
    }


}
