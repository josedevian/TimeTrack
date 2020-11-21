package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Project

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table")
    fun getAll(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(project: Project)

    @Update
    suspend fun update(project: Project)

    @Delete
    suspend fun delete(project: Project)
}