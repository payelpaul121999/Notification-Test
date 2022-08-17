package com.palpayel.notificationtest.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
 data class Student (

        @PrimaryKey(autoGenerate = true)
         var id:Int=0,

        @ColumnInfo(name = "firstName")
        var firstName:String,

        @ColumnInfo(name = "lastName")
        var lastName:String,

        @ColumnInfo(name = "address")
        var address:String,
  )