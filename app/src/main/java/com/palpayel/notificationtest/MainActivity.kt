package com.palpayel.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.palpayel.notificationtest.RoomDB.Student
import com.palpayel.notificationtest.RoomDB.StudentDataBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val channelID="com.palpayel.notificationtest"
    private var notificationManager:NotificationManager? = null
    private val KEY_REPLY="key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



        val dbInstance =StudentDataBase.getStudentDataBase(this)

        saveBtn.setOnClickListener {
            GlobalScope.launch {
                val student = Student(firstName = firstName.text.toString(), lastName = emailName.text.toString(), address = address.text.toString())
                dbInstance?.getStudentDao()?.insertStudent(student)

           val studentList = dbInstance?.getStudentDao()?.loadAll()
                Log.e("Students","$studentList")
            }
        }




        createNotificationChannel(channelID,"DemoChannel","This is Demo")

        button.setOnClickListener {
            displayNotification()
        }


    }
    private fun createNotificationChannel(id:String,name:String,channelDescription:String){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
             val importance=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel(id,name,importance).apply {
                description=channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        val tapResultIntent=Intent(this,SecondActivity::class.java)
        val pendingIntent:PendingIntent=PendingIntent.getActivity(this,0,tapResultIntent,PendingIntent.FLAG_UPDATE_CURRENT)

//action 2
        val intent2=Intent(this,DetailsActivity::class.java)
        val pendingIntent2:PendingIntent=PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT)
        val action2:NotificationCompat.Action=NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()

//action 3
        val intent3=Intent(this,SetttingsActivity::class.java)
        val pendingIntent3:PendingIntent=PendingIntent.getActivity(this,0,intent3,PendingIntent.FLAG_UPDATE_CURRENT)
        val action3:NotificationCompat.Action=NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()
//reply action

val remoteInput:RemoteInput= RemoteInput.Builder(KEY_REPLY).run {
    setLabel("Insert your name here").build()
}
val replyAction :  NotificationCompat.Action=NotificationCompat.Action.Builder(0,"REPLY",pendingIntent).addRemoteInput(remoteInput).build()


        val notification=NotificationCompat
            .Builder(this@MainActivity,channelID)
            .setContentTitle("Demo Title")
            .setContentText("Hey Payel")
            .setSmallIcon(android.R.drawable.btn_star)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()

        notificationManager?.notify(notificationId,notification)

    }

}