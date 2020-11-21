package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.Label

@Dao
interface LabelDao {

    @Query("SELECT * FROM label_table")
    fun getAll(): LiveData<List<Label>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(label: Label)

    @Update
    suspend fun update(label: Label)

    @Delete
    suspend fun delete(label: Label)
}