package com.annda.taskapp.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val name: String = "",
    val description: String = "",
    val writeOn: Long
)