package com.example.nacho.notificationfirebaseandroid

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token: ${token}")

    }

}
