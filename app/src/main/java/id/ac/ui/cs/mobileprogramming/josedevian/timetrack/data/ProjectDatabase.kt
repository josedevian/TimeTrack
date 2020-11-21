package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Project

@Database(entities = [Project::class], version = 2, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var instance: ProjectDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                ProjectDatabase::class.java,
                "project-database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}