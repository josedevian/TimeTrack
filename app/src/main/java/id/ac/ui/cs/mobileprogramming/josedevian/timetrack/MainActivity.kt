package id.ac.ui.cs.mobileprogramming.josedevian.timetrack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.ListFragment
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.Stopwatch
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Stopwatch(), "Time")
        adapter.addFragment(ListFragment(), "Logs")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_timer_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_view_list_24)
    }
}