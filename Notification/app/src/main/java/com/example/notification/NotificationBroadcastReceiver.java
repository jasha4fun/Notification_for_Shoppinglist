package com.example.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NotificationBroadcastReceiver", "Received intent: " + intent.getAction());

        context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        NotificationManager notificationManager = (NotificationManager) context
                .getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(intent.getIntExtra(NotificationService.NOTIFICATION_ID, 0));
    }
}
