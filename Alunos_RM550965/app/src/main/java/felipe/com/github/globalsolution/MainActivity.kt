package felipe.com.github.globalsolution

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import felipe.com.github.globalsolution.model.EventModel
import felipe.com.github.globalsolution.viewmodel.EventViewModel
import felipe.com.github.globalsolution.viewmodel.EventViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eventos Extremos"

        val editLocationName = findViewById<EditText>(R.id.editLocationName)
        val editEventType = findViewById<EditText>(R.id.editEventType)
        val editImpactDegree = findViewById<EditText>(R.id.editImpactDegree)
        val editEventDate = findViewById<EditText>(R.id.editEventDate)
        val editAffectedPeople = findViewById<EditText>(R.id.editAffectedPeople)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonEventList = findViewById<Button>(R.id.buttonEventList)
        val buttonIdentification = findViewById<Button>(R.id.buttonIdentification)

        val viewModelFactory = EventViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]

        buttonAdd.setOnClickListener {
            val location = editLocationName.text.toString()
            val type = editEventType.text.toString()
            val impact = editImpactDegree.text.toString()
            val date = editEventDate.text.toString()
            val affected = editAffectedPeople.text.toString()

            if (location.isEmpty() || type.isEmpty() || impact.isEmpty() || date.isEmpty() || affected.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val event = EventModel(
                    locationName = location,
                    eventType = type,
                    impactDegree = impact,
                    eventDate = date,
                    affectedPeople = affected.toInt()
                )

                viewModel.addEvent(event)

                // Limpar campos
                editLocationName.text.clear()
                editEventType.text.clear()
                editImpactDegree.text.clear()
                editEventDate.text.clear()
                editAffectedPeople.text.clear()
            }
        }

        buttonEventList.setOnClickListener {
            startActivity(Intent(this, EventListActivity::class.java))
        }

        buttonIdentification.setOnClickListener {
            startActivity(Intent(this, IdentificationActivity::class.java))
        }
    }
}
