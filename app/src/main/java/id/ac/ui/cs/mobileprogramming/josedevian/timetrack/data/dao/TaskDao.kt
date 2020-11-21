package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTask(): LiveData<List<Task>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(task: id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task)

    @Delete
    suspend fun delete(task: id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Task)

}