package com.annda.taskapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.annda.taskapp.models.db.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun createTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun allTasks(): LiveData<List<Task>>


}