package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MainActivity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters.TaskListAdapter
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel.TaskViewModel


class ListFragment() : Fragment() {
    private lateinit var viewModel: TaskViewModel
    private lateinit var taskCounter: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainAdapter = TaskListAdapter(this) { task, i ->
            Log.d("Main", "yes")
        }

        val itemView = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = itemView.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.getTasks()?.observe(viewLifecycleOwner, Observer {
            mainAdapter.setTasks(it)
        })

        recyclerView.adapter = mainAdapter

        taskCounter = itemView.findViewById(R.id.taskCount)
        var format: String = String.format("Tasks (%d)", (activity as MainActivity).totalTaskCount)

        taskCounter.text = format
        return itemView
    }
}