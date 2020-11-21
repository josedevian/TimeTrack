package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @ColumnInfo(name = "taskName") val taskName: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name = "project") val project: String,
    @ColumnInfo(name = "label") val label: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,)