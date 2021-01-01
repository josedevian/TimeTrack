package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.GeofenceRepository

import javax.inject.Inject

class GeoFenceViewModel @Inject constructor(var geoFenceRepo: GeofenceRepository) : ViewModel() {

//    val geoFenceItems: LiveData<List<GeoFence>> = geoFenceRepo.geoFences
//
//    fun insert(geoFence: GeoFence) {
//        geoFenceRepo.insert(geoFence)
//    }
//
//    fun delete(geoFence: GeoFence) {
//        geoFenceRepo.delete(geoFence)
//    }
//
//    fun update(geoFence: GeoFence) {
//        geoFenceRepo.update(geoFence)
//    }

}