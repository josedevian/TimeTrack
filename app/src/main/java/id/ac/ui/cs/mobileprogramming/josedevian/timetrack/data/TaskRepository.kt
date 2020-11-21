package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao.TaskDao
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository (application: Application) {

    private val taskDao: TaskDao?
    private var tasks: LiveData<List<Task>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        taskDao = db?.taskDao()
        tasks = taskDao?.getTask()
    }

    fun getTasks(): LiveData<List<Task>>? {
        return tasks
    }

    fun insert(task: Task) = runBlocking {
        this.launch(Dispatchers.IO) {
            taskDao?.insert(task)
        }
    }

    fun delete(task: Task) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                taskDao?.delete(task)
            }
        }
    }
}