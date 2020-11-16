package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER_BY id DESC")
    fun getAll(): LiveData<List<@JvmSuppressWildcards TaskEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)

}