package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "task")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "taskName") val taskName: String?,
    @ColumnInfo(name = "startTime") val startTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "endTime") val endTime: Long = startTime,
    @ColumnInfo(name = "duration") val duration: Long = endTime - startTime,
    @ColumnInfo(name = "date") val date: LocalDate?,
    @ColumnInfo(name = "project") val project: String?,
    @ColumnInfo(name = "label") val label: String?, )