package com.annda.taskapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annda.taskapp.R
import com.annda.taskapp.models.db.Task

class TaskAdapter(private var tasks: MutableList<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    inner class TaskViewHolder(v: View): RecyclerView.ViewHolder(v){
        var title: TextView = v.findViewById(R.id.task_item_title)
        var desc: TextView = v.findViewById(R.id.task_item_desc)

    }

    private var listener: ((Task) -> Unit)? = null

    fun setOnTaskTapListener(listener: ((Task) -> Unit)){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_task_item, parent, false)

        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.name
        holder.desc.text = task.description

        holder.itemView.setOnClickListener(View.OnClickListener {
            listener?.invoke(task)
        })
    }

    fun swapData(tasks: List<Task>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }
}

