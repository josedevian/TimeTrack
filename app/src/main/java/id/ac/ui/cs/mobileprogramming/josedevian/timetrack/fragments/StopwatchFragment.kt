package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MainActivity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import kotlinx.android.synthetic.main.fragment_stopwatch.view.*


class StopwatchFragment : Fragment() {

    var duration: String? = null
    var title: String? = null
    var date: String? = null
    private lateinit var taskViewModel: TaskViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        val stopwatch = view.findViewById<TextView>(R.id.stopwatch)
        val startStopButton = view.findViewById<Button>(R.id.start_stop_stopwatch)
        val resetButton = view.findViewById<Button>(R.id.reset_stopwatch)
        val saveButton: View = view.findViewById<Button>(R.id.save_button)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        if((activity as MainActivity).stopwatchIsRunning) {
            saveButton.isEnabled = false
            startStopButton.isEnabled = true
        }
        (activity as MainActivity).stopwatchText = stopwatch

        startStopButton.setOnClickListener {
            if((activity as MainActivity).stopwatchIsRunning) {
                (activity as MainActivity).pauseStopwatch()
                startStopButton.setText(R.string.start)
                saveButton.isEnabled = true
                resetButton.isEnabled = true
            }
            else {
                startStopButton.setText(R.string.stop)
                (activity as MainActivity).startStopwatch()
                saveButton.isEnabled = false
                resetButton.isEnabled = false
            }
        }

        resetButton.setOnClickListener {
            (activity as MainActivity).resetStopwatch()
            stopwatch.text = "00:00.00"
            saveButton.isEnabled = false
        }

        saveButton.setOnClickListener {
            duration = stopwatch.text.toString()
            date = (activity as MainActivity).taskDate
            val builder = AlertDialog.Builder((activity as MainActivity))
            val dialogLayout = layoutInflater.inflate(R.layout.text_input_dialog, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edit_task_name)

            with(builder) {
                setTitle(R.string.save_alert)
                setPositiveButton(R.string.save) {dialog, which ->
                    title = editText.text.toString()
                    taskViewModel.insertTask(Task(title!!, date.toString(), duration!!,
                        null.toString(), null.toString()
                    ))
                    (activity as MainActivity).resetStopwatch()
                    val successToast = Toast.makeText((activity as MainActivity), R.string.success_save, Toast.LENGTH_SHORT)
                    successToast.show()
                }
                setNegativeButton(R.string.cancel) {dialog, which ->
                    Log.d("Main", "cancel button clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

        return view

    }

}