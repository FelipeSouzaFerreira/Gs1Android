package felipe.com.github.globalsolution.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import felipe.com.github.globalsolution.data.EventDao
import felipe.com.github.globalsolution.data.EventDatabase
import felipe.com.github.globalsolution.model.EventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private val eventDao: EventDao
    val eventsLiveData: LiveData<List<EventModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EventDatabase::class.java,
            "events_database"
        ).build()
        eventDao = database.eventDao()


        eventsLiveData = eventDao.getAll()
    }

    fun addEvent(event: EventModel) {

        viewModelScope.launch(Dispatchers.IO) {

            eventDao.insert(event)
        }
    }

    fun removeEvent(event: EventModel) {

        viewModelScope.launch(Dispatchers.IO) {
            eventDao.delete(event)
        }
    }
}