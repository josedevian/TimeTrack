package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.ListFragment
import kotlinx.android.synthetic.main.list_items.view.*

class TaskListAdapter(private val context: ListFragment, private val listener: (Task, Int) -> Unit) :
    RecyclerView.Adapter<TaskViewHolder>() {

    private var tasks = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_items,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, tasks[position], listener)
        }
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context: ListFragment, task: Task, listener: (Task, Int) -> Unit) {
        itemView.task_name.text = task.taskName
        itemView.task_date_time.text = task.date
        itemView.task_duration.text = task.duration
    }
}