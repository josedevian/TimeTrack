package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location (
    @ColumnInfo(name = "locationName")
    val locationName: String?,

    @ColumnInfo(name = "locationLong")
    val longitude: String?,

    @ColumnInfo(name = "locationLat")
    val latitude: String?,

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
