package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.LabelDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.TaskDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.LabelEntity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.ProjectEntity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.TaskEntity

@Database(entities = [TaskEntity::class, ProjectEntity::class, LabelEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
    abstract fun labelDao(): LabelDao

    companion object {

        private const val DB_NAME = "TIMETRACK_DB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
            }
            return instance
        }
    }
}