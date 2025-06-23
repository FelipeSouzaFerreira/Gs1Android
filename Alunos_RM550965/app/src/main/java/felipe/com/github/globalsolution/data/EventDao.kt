package felipe.com.github.globalsolution.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import felipe.com.github.globalsolution.model.EventModel

@Dao
interface EventDao {

    @Query("SELECT * FROM EventModel ORDER BY id DESC")
    fun getAll(): LiveData<List<EventModel>>

    @Insert
    suspend fun insert(event: EventModel)

    @Delete
    suspend fun delete(event: EventModel)
}
