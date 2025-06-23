package felipe.com.github.globalsolution.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import felipe.com.github.globalsolution.R
import felipe.com.github.globalsolution.model.EventModel

class EventAdapter(
    private val onDeleteClick: (EventModel) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val events = mutableListOf<EventModel>()

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val locationTextView: TextView = view.findViewById(R.id.textLocation)
        val eventTypeTextView: TextView = view.findViewById(R.id.textEventType)
        val impactDegreeTextView: TextView = view.findViewById(R.id.textImpactDegree)
        val eventDateTextView: TextView = view.findViewById(R.id.textEventDate)
        val affectedPeopleTextView: TextView = view.findViewById(R.id.textAffectedPeople)
        val deleteButton: Button = view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.locationTextView.text = "Local: ${event.locationName}"
        holder.eventTypeTextView.text = "Tipo: ${event.eventType}"
        holder.impactDegreeTextView.text = "Impacto: ${event.impactDegree}"
        holder.eventDateTextView.text = "Data: ${event.eventDate}"
        holder.affectedPeopleTextView.text = "Pessoas afetadas: ${event.affectedPeople}"

        holder.deleteButton.setOnClickListener {
            onDeleteClick(event)
        }
    }

    override fun getItemCount() = events.size

    fun updateEvents(newEvents: List<EventModel>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }
}
