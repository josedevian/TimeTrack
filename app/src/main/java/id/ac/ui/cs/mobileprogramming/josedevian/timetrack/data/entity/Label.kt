package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task


@Entity(tableName = "label_table")
data class Label (
    @ColumnInfo(name = "labelColor")
        val labelColor: String? = "#FFFFFF",

    @ColumnInfo(name = "labelTasks")
        val labelTasks: String?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    )