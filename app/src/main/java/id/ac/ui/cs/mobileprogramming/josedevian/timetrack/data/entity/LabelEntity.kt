package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task


@Entity(tableName = "label_table")
data class LabelEntity (
    @PrimaryKey @NonNull @ColumnInfo(name = "labelColor")
        val labelColor: String?,

    @ColumnInfo(name = "labelTasks")
        val labelTasks: List<Task>,

    )