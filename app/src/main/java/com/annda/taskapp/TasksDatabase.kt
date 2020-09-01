package com.annda.taskapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.annda.taskapp.dao.TaskDao
import com.annda.taskapp.models.db.Task

@Database(version = 1, entities = [Task::class], exportSchema = false)
abstract class TasksDatabase: RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    TasksDatabase::class.java,
                    "tasks_database")
                    .addCallback(TaskDatabaseCallback())
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun taskDao(): TaskDao
}

private class TaskDatabaseCallback: RoomDatabase.Callback(){

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        println("DEBUG: Database created")
    }
}