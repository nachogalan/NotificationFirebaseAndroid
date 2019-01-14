package com.example.nacho.notificationfirebaseandroid

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"

    companion object {
        const val CHANNEL_ID = "some_channel_id"

    }

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Message: ${remoteMessage.from}")
        // TODO(developer): Handle FCM messages here.
       // createNotification(remoteMessage.notification?.body)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData())

            if (/* Check if data needs to be processed by long running job */ true) {

                //showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)

            } else {
                // Handle message within 10 seconds

            }

        }
        if (remoteMessage.notification != null) {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }



    fun createNotification(msg: String?) {

        var notificationId = 1
        var mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_bg)

            .setContentTitle("Notification")
            .setContentText(msg)

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, mBuilder.build())
        }
    }



    private fun showNotification(title: String?, body: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}