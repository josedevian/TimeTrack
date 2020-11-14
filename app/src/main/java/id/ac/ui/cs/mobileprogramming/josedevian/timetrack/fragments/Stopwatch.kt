package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R


class Stopwatch : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        val stopwatch = view.findViewById(R.id.stopwatch)
        val startButton = view.findViewById(R.id.start_stopwatch)
        val pauseButton = view.findViewById(R.id.pause_stopwatch)
        val resetButton = view.findViewById(R.id.reset_stopwatch)

        if ((activity as MainActivity).stopwatchIsRunning) {
            startButton.isEnabled = false
            resetButton.isEnabled = false
        }
        (activity as MainActivity).stopwatchText = stopwatch

        startButton.setOnClickListener {
            (activity as MainActivity).startStopwatch()
            startButton.isEnabled = false
            resetButton.isEnabled = false
        }
        pauseButton.setOnClickListener {
            (activity as MainActivity).pauseStopwatch()
            startButton.isEnabled = true
            resetButton.isEnabled = true
        }
        resetButton.setOnClickListener {
            (activity as MainActivity).resetStopwatch()
            stopwatch.text = "00:00:00"
        }

        return view
    }

}