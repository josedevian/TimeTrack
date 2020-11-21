package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.TaskRepository
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    var taskRepository = TaskRepository(application)
    private var tasks: LiveData<List<id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task>>? = taskRepository.getTasks()

    fun insertTask(task: Task) {
        taskRepository.insert(task)
    }

    fun getTasks(): LiveData<List<id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task>>? {
        return tasks
    }

    fun deleteTask(task: Task) {
        taskRepository.delete(task)
    }
}