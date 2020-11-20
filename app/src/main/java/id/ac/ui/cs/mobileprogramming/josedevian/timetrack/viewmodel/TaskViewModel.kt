package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.TaskRepository
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    fun getTask() = taskRepository.getTasks()

    fun addTask(task: Task) = taskRepository.insert(task)
}