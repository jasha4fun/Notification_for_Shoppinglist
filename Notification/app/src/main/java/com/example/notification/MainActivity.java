package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ProductCreatedReceiver productCreatedReceiver = new ProductCreatedReceiver();
    private NotificationBroadcastReceiver notificationBroadcastReceiver = new NotificationBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter productAddedFilter = new IntentFilter();
        getResources().getString(R.string.shoppinglist_action_product_created);
        String permission = getResources().getString(R.string.application_permission_broadcast_receive);
        registerReceiver(productCreatedReceiver, productAddedFilter, permission, null);
        Log.i("MainActivity", "productCreatedReceiver registred");

        IntentFilter hideFilter = new IntentFilter();
        hideFilter.addAction(getResources().getString(R.string.shoppinglist_action_product_list));
        hideFilter.addAction(getResources().getString(R.string.shoppinglist_action_product_edited));
        registerReceiver(notificationBroadcastReceiver, hideFilter);
        Log.i("MainActivity", "notificationBroadcastReceiver registred");

    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(productCreatedReceiver);
        Log.i("MainActivity", "productCreatedReceiver unregistered");
        unregisterReceiver(notificationBroadcastReceiver);
        Log.i("MainActivity", "notificationBroadcastReceiver unregistered");

        super.onDestroy();
    }
}
