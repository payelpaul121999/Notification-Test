package com.palpayel.notificationtest.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDataBase :RoomDatabase(){
 abstract fun getStudentDao():DaoStudent

 companion object{
     var INSTANCE : StudentDataBase?=null

     fun getStudentDataBase(context: Context): StudentDataBase?{
         if(INSTANCE==null){
             INSTANCE= Room.databaseBuilder(
                 context.applicationContext,
                StudentDataBase::class.java,
                 "studentDb")
                 .build()
         }

         return INSTANCE
     }

     fun cleanDbObject(){
         INSTANCE = null
     }
 }

 }