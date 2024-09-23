package com.example.recyclerview2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note
import com.example.recyclerview2.models.Planete

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    // Code pour identifier une modification
    private val EDIT_NOTE_REQUEST = 1
    private var currentNotePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Exemple de données de notes
        val notes = mutableListOf(
            Note("Meeting with Bob", "Discuss project status", "2024-09-23"),
            Note("Shopping List", "Buy milk, bread, and eggs", "2024-09-22"),
            Note("Workout", "Gym at 7 PM", "2024-09-21")
        )

        noteAdapter = NoteAdapter(notes,
            editAction = { note, position ->
                // Enregistrer la position de la note modifiée
                currentNotePosition = position
                // Lancer l'activité de modification
                val intent = Intent(this, EditNoteActivity::class.java)
                intent.putExtra("note_title", note.title)
                intent.putExtra("note_description", note.description)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }
        )
        recyclerView.adapter = noteAdapter
    }

    // Recevoir les données modifiées après l'édition
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            // Obtenir les nouvelles valeurs de titre et de description
            val newTitle = data?.getStringExtra("new_note_title")
            val newDescription = data?.getStringExtra("new_note_description")

            // Mettre à jour la note dans la liste et notifier l'adaptateur
            if (newTitle != null && newDescription != null && currentNotePosition != -1) {
                noteAdapter.updateNote(currentNotePosition, newTitle, newDescription)
            }
        }
    }
}