package com.palpayel.notificationtest

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }
    private  fun receiveInput(){
        val KEY_REPLY="key_reply"
        val intent:Intent = this.intent
        val remoteInput:Bundle = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput !=null){
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            resultTV.text=inputString
          val channelID="com.palpayel.notificationtest"
            val notificationID=45

            val repliedNotification =NotificationCompat.Builder(this,channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply receive")
                .build()
            val notificationManager :NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationID,repliedNotification)



        }
    }

}