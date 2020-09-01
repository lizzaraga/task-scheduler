package com.annda.taskapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.annda.taskapp.R
import com.annda.taskapp.TaskViewModel
import com.annda.taskapp.databinding.BsAddTaskBinding
import com.annda.taskapp.models.db.Task

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddTaskFragment: BottomSheetDialogFragment() {

    private lateinit var viewBinding : BsAddTaskBinding

    private var title: String? = null
    private var description: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = BsAddTaskBinding.inflate(layoutInflater, container, false)



        viewBinding.addTask.setOnClickListener(View.OnClickListener {
            title = viewBinding.taskTitle.editText?.text.toString()
            description = viewBinding.taskDesc.editText?.text.toString()

            if(title!!.isNotEmpty() && description!!.isNotEmpty()){
                ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
                    .create(TaskViewModel::class.java)
                    .createTask(
                        Task(
                            name = title!!,
                            description = description!!,
                            writeOn = Calendar.getInstance().timeInMillis
                        )
                    )

                Toast.makeText(context, "Task add", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            }
            else Toast.makeText(context, "Please fill all fields to add task !", Toast.LENGTH_SHORT)
                .show()
        })


        return viewBinding.root
    }

    companion object {
        fun newInstance(): AddTaskFragment{
            val args = Bundle()

            val fragment = AddTaskFragment()
            fragment.arguments = args
            return fragment
        }

        const val TAG = "AddTaskFragment"
    }
}