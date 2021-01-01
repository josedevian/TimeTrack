package id.ac.ui.cs.mobileprogramming.josedevian.timetrack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.*
import android.os.BatteryManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters.TaskListAdapter
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters.ViewPagerAdapter
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.ListFragment
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.StopwatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    var stopwatchIsRunning: Boolean = false
    var stopwatchText: TextView? = null
    var taskDate: String? = null
    var totalTaskCount: Int = 0
    private var millisecondTime: Long = 0
    private var startTime: Long = 0
    private var timeBuff: Long = 0
    private var updateTime: Long = 0
    private var seconds: Int = 0
    private var minutes: Int = 0
    private var milliSeconds: Int = 0
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private val CHANNEL_ID = "channel_id_battery_low"
    private val notificationId = 101
    var duration: String? = null
    lateinit var mainAdapter: TaskListAdapter

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        setUpTabs()

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(batteryBroadcastReceiver, intentFilter)

        mHandler = Handler()
        mRunnable = Runnable {
            millisecondTime = SystemClock.uptimeMillis() - startTime
            updateTime = timeBuff + millisecondTime
            seconds = (updateTime / 1000).toInt()
            minutes = seconds / 60
            seconds %= 60
            milliSeconds = (updateTime % 100).toInt()
            mHandler.postDelayed(this.mRunnable, 0)
            stopwatchText?.text =
                (String.format("%02d", minutes) + ":" + String.format(
                    "%02d",
                    seconds
                ) + ":" + String.format("%02d", milliSeconds))
        }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(StopwatchFragment(), "Time")
        adapter.addFragment(ListFragment(), "Logs")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_timer_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_view_list_24)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startStopwatch() {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm")
        taskDate = now.format(formatter).toString()
        startTime = SystemClock.uptimeMillis()
        mHandler.postDelayed(mRunnable, 0)
        stopwatchIsRunning = true
    }

    fun pauseStopwatch() {
        timeBuff += millisecondTime
        mHandler.removeCallbacks(mRunnable)
        stopwatchIsRunning = false
    }

    fun resetStopwatch() {
        taskDate = null
        millisecondTime = 0
        startTime = 0
        timeBuff = 0
        updateTime = 0
        seconds = 0
        minutes = 0
        milliSeconds = 0
    }

    fun saveStopwatch() {
        duration = stopwatchText.toString()
    }

    fun hideKeyboard() {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.SHOW_FORCED
        )
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to close this app?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
                finish()
            }
            .setNegativeButton("No") { _, _ ->
            }
            .show()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.notification_title.toString()
            val descriptionText = R.string.notification_message.toString()
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val notificationTitle = resources.getString(R.string.notification_title)
        val notificationText = resources.getString(R.string.notification_message)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryBroadcastReceiver)
    }

    private val batteryBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if((intent?.action == "android.intent.action.BATTERY_LOW") && stopwatchIsRunning) {
                sendNotification()
            }
        }
    }

    external fun increaseTaskCount(count: Int): Int

}