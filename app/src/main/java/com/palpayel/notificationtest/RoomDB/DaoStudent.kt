package com.palpayel.notificationtest.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoStudent {

    @Query("SELECT * FROM Student")
    fun loadAll():List<Student>

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * from Student where firstName==:name")
    fun getStudentListByName(name: String) : List<Student>

    @Insert
    fun insertStudent(student: Student)
}