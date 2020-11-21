package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task
import kotlinx.android.synthetic.main.list_items.view.*

class TaskListAdapter(private val context: Context?, private val listener: List<id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task>) :
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

    fun bindItem(context: Context, task: Task, listener: List<id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task>) {
        itemView.task_name.text = task.taskName
        itemView.task_date_time.text = task.date
        itemView.task_duration.text = task.duration

//        itemView.setOnClickListener {
//            listener(task, layoutPosition)
//        }
    }
}