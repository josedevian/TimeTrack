package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task

@Entity(tableName = "project_table")
data class ProjectEntity (
    @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

    @ColumnInfo(name = "projectName")
        val projectName: String?,

    @ColumnInfo(name = "projectTasks")
        val projectTasks: List<Task>,
)