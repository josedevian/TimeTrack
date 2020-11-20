package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.utils

import android.content.Context
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.AppDatabase
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.TaskRepository
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel.TaskViewModelFactory

object InjectorUtils {
    fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory {
        val taskRepository = TaskRepository.getInstance(
            AppDatabase.getInstance(context).taskDao())

        return TaskViewModelFactory(
            taskRepository
        )
    }
}