package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.TaskDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task-database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}