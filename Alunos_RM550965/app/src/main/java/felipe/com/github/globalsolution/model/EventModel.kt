package felipe.com.github.globalsolution.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class EventModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val locationName: String,
    val eventType: String,
    val impactDegree: String,
    val eventDate: String,
    val affectedPeople: Int
)