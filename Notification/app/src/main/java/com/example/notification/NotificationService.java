package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Action;

import java.util.Random;

public class NotificationService extends Service {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NotificationService", "Service created!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationService", "Service started");

        int notificationId = new Random().nextInt();

        Intent editIntent = new Intent(getString(R.string.shoppinglist_action_product_edited));
        editIntent.putExtra("id", intent.getIntExtra("id", 0));
        editIntent.putExtra(NOTIFICATION_ID, notificationId);
        PendingIntent editPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent listIntent = new Intent(getString(R.string.shoppinglist_action_product_list));
        listIntent.putExtra(NOTIFICATION_ID, notificationId);
        PendingIntent listPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, listIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentText("Shopping list product added: " + intent.getStringExtra("type"))
                .setContentText("Amount: " + Integer.toString(intent.getIntExtra("amount", 0)))
                .setSmallIcon(R.drawable.ic_notification_img)
                .addAction(new Action(R.drawable.ic_edit_icon, "Edit product", editPendingIntent))
                .addAction(new Action(R.drawable.ic_list_icon, "List all", listPendingIntent))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);

        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i("NotificationService", "Service bound");

        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("NotificationService", "Service destroyed");
    }
}
