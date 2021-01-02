//package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.geofence
//
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.widget.Toast
//import com.google.android.gms.location.Geofence
//import com.google.android.gms.location.GeofencingEvent
//import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MainActivity
//import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.MapsActivity
//
//class GeofenceBroadcastReceiver : BroadcastReceiver() {
//
//    var TAG: String = "GeofenceBroadcastReceiv"
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        var geofencingEvent: GeofencingEvent = GeofencingEvent.fromIntent(intent)
//
//        if (geofencingEvent.hasError()) {
//            Log.d(TAG, "onReceive: Error receiving geofence event...");
//            return;
//        }
//
//        val transitionType = geofencingEvent.geofenceTransition
//
//        when (transitionType) {
//            Geofence.GEOFENCE_TRANSITION_ENTER -> {
//                // todo start stopwatch to start task, send notification too
//
//            }
//            Geofence.GEOFENCE_TRANSITION_DWELL -> {
//                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show()
//                notificationHelper.sendHighPriorityNotification(
//                    "GEOFENCE_TRANSITION_DWELL", "",
//                    MapsActivity::class.java
//                )
//            }
//            Geofence.GEOFENCE_TRANSITION_EXIT -> {
//                // todo stop stopwatch and save task, send notification too
//            }
//        }
//    }
//}