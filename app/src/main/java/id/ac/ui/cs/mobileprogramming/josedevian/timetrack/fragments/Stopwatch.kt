package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_stopwatch.view.*


class Stopwatch : Fragment() {

    private var seconds = 0
    private var running = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
        }
        runTimer()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
    }


    fun onClickStart(v: View?) {
        running = true
    }

    fun onClickStop(v: View?) {
        running = false
    }

    fun onClickReset(v: View?) {
        running = false
        seconds = 0
    }


    private fun runTimer() {
        val timeView = findViewById(R.id.text_view_stopwatch) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time: String = java.lang.String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d",
                    hours,
                    minutes,
                    secs
                )
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    fun onClickSave(v: View?) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setTitle(getString())
    }

}