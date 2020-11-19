package id.ac.ui.cs.mobileprogramming.josedevian.timetrack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.*
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.adapters.ViewPagerAdapter
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.ListFragment
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.StopwatchFragment
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.utils.BatteryPercentageChecker
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var stopwatchIsRunning: Boolean = false
    var stopwatchText: TextView? = null
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
    var duration: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        setUpTabs()

        mHandler = Handler()
        mRunnable = Runnable {
            millisecondTime = SystemClock.uptimeMillis() - startTime
            updateTime = timeBuff + millisecondTime;
            seconds = (updateTime / 1000).toInt();
            minutes = seconds / 60;
            seconds %= 60;
            milliSeconds = (updateTime % 100).toInt();
            mHandler.postDelayed(this.mRunnable, 0);
            stopwatchText?.text =
                (String.format("%02d", minutes) + ":" + String.format(
                    "%02d",
                    seconds
                ) + ":" + String.format("%02d", milliSeconds));
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

    fun startStopwatch() {
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
        millisecondTime = 0
        startTime = 0
        timeBuff = 0
        updateTime = 0
        seconds = 0
        minutes = 0
        milliSeconds = 0
    }

    fun saveStopwatch() {
        duration = timeBuff
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

    fun showSaveTaskDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.text_input_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.edit_task_name)

        with(builder) {
            setTitle(R.string.save_alert)
            setPositiveButton(R.string.save) { dialog, which -> }
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val newOrientation: Int = newConfig.orientation
        if (newOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            adapter.removeFragment(ListFragment(), "Logs")
        }
        if (newOrientation == Configuration.ORIENTATION_PORTRAIT) {
            adapter.addFragment(ListFragment(), "Logs")
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.notification_title.toString()
            val notifDesc = R.string.notification_message.toString()
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = notifDesc.toString()
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(R.string.notification_title.toString())
            .setContentText(R.string.notification_message.toString())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

//    fun batteryNotifier() {
//        val batteryLevel = getBatteryPercentage()
//        if(getBatteryPercentage() <= 10)
//    }
//
//    fun getBatteryPercentage(context: Context): Int {
//        return if (Build.VERSION.SDK_INT >= 21) {
//            val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
//            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
//        } else {
//            val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
//            val batteryStatus: Intent? = context.registerReceiver(null, iFilter)
//            val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
//            val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
//            val batteryPct = level / scale.toDouble()
//            (batteryPct * 100).toInt()
//        }
//    }
}