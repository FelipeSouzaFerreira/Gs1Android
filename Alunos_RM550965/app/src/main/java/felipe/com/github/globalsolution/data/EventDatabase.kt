package felipe.com.github.globalsolution.data

import androidx.room.Database
import androidx.room.RoomDatabase
import felipe.com.github.globalsolution.model.EventModel
@Database(entities = [EventModel::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}