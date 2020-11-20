package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.TaskRepository

class TaskViewModelFactory(private val taskRepository: TaskRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(
            taskRepository
        ) as T
    }
}