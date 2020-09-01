package com.annda.taskapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.annda.taskapp.models.db.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

    private val taskRepository: TaskRepository

    init {
        val taskDao = TasksDatabase
            .getDatabase(application)
            .taskDao()
        taskRepository = TaskRepository(taskDao)
    }

    fun createTask(task: Task) = viewModelScope.launch{
        taskRepository.createTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun allTasks(): LiveData<List<Task>>{
        return taskRepository.allTasks()
    }
}