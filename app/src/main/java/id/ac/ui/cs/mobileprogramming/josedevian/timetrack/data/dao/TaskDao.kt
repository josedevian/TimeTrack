package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER_BY id DESC")
    fun getTask(): LiveData<List<Task>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

}