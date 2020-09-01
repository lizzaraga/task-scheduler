package com.annda.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annda.taskapp.adapters.TaskAdapter
import com.annda.taskapp.databinding.ActivityMainBinding
import com.annda.taskapp.fragments.AddTaskFragment
import com.annda.taskapp.models.db.Task
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        taskViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(TaskViewModel::class.java)

        taskRecyclerView = viewBinding.taskRecyclerView
        taskAdapter = TaskAdapter(mutableListOf())
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        taskRecyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))

        taskAdapter.setOnTaskTapListener { task ->
            Toast.makeText(this@MainActivity, task.toString(), Toast.LENGTH_SHORT).show()
        }

        taskViewModel.allTasks().observe(this, Observer {
            Log.d("DEBUG", it.toString())
            taskAdapter.swapData(it)
        })

        viewBinding.openBsAddTask.setOnClickListener(View.OnClickListener {
            val addTaskFragment = AddTaskFragment()
            addTaskFragment.show(supportFragmentManager, AddTaskFragment.TAG)
        })
    }
}