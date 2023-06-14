package com.example.taskmanager.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.example.taskmanager.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        createNotification(message)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(message: RemoteMessage) {

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        //Log.e("ololo", "title: " + message.notification?.title)
        // Log.e("ololo", "desc: " + message.notification?.body)

        val notification: Notification.Builder = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1, notification.build());
    }

    companion object {
        const val CHANNEL_ID = "HEADS_UP_NOTIFICATION_55"

        @RequiresApi(Build.VERSION_CODES.O)
        val channel = NotificationChannel(
            CHANNEL_ID,
            "TASK_55",
                    NotificationManager.IMPORTANCE_HIGH
        )
    }
}
