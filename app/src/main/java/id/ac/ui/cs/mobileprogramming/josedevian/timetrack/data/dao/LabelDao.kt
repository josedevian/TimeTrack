package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import id.ac.ui.cs.mobileprogramming.josedevian.timetrack.data.entity.LabelEntity

@Dao
interface LabelDao {

    @Query("SELECT * FROM label_table")
    fun getAll(): LiveData<List<@JvmSuppressWildcards LabelEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(label: LabelEntity)

    @Update
    fun update(label: LabelEntity)

    @Delete
    fun delete(label: LabelEntity)
}