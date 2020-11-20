package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MainActivity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import kotlinx.android.synthetic.main.fragment_stopwatch.view.*


class StopwatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory =
        val view = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        val stopwatch = view.findViewById<TextView>(R.id.stopwatch)
        val startStopButton = view.findViewById<Button>(R.id.start_stop_stopwatch)
        val resetButton = view.findViewById<Button>(R.id.reset_stopwatch)
        val saveButton: View = view.findViewById<Button>(R.id.save_button)

        if((activity as MainActivity).stopwatchIsRunning) {
//            saveButton.isVisible = true
            saveButton.isEnabled = false
            startStopButton.isEnabled = true
        }
        (activity as MainActivity).stopwatchText = stopwatch

        startStopButton.setOnClickListener {
            if((activity as MainActivity).stopwatchIsRunning) {
                (activity as MainActivity).pauseStopwatch()
                startStopButton.setText(R.string.start)
//                saveButton.isVisible = true
                saveButton.isEnabled = true
                resetButton.isEnabled = true
            }
            else {
                startStopButton.setText(R.string.stop)
                (activity as MainActivity).startStopwatch()
//                saveButton.isVisible = false
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
            (activity as MainActivity).saveStopwatch()
        }

        return view

    }

}