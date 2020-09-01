package com.annda.taskapp

import androidx.lifecycle.LiveData
import com.annda.taskapp.dao.TaskDao
import com.annda.taskapp.models.db.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun createTask(task: Task){
        taskDao.createTask(task)
    }

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    fun allTasks(): LiveData<List<Task>>{
        return taskDao.allTasks()
    }


}