package felipe.com.github.globalsolution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import felipe.com.github.globalsolution.viewmodel.EventAdapter
import felipe.com.github.globalsolution.viewmodel.EventViewModel
import felipe.com.github.globalsolution.viewmodel.EventViewModelFactory

class EventListActivity : AppCompatActivity() {

    private lateinit var viewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        val toolbar: Toolbar = findViewById(R.id.toolbarEventList)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        supportActionBar?.title = "Eventos Registrados"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        eventAdapter = EventAdapter { event -> viewModel.removeEvent(event) }
        recyclerView.adapter = eventAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModelFactory = EventViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]

        viewModel.eventsLiveData.observe(this) { events ->
            eventAdapter.updateEvents(events)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
