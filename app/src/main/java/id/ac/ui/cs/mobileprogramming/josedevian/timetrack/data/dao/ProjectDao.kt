package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.ProjectEntity

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project_table")
    fun getAll(): LiveData<List<@JvmSuppressWildcards ProjectEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(project: ProjectEntity)

    @Update
    fun update(project: ProjectEntity)

    @Delete
    fun delete(project: ProjectEntity)
}