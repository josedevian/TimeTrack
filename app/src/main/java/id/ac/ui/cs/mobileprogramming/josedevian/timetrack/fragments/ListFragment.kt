package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MainActivity
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.R
import kotlinx.android.synthetic.main.activity_main.*


class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val itemView = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = itemView.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(activity, 1)
        }

        if ((activity as MainActivity).mBound) {
            (activity as MainActivity).mService.fetchData()
            (activity as MainActivity).mService.
        }
    }
}